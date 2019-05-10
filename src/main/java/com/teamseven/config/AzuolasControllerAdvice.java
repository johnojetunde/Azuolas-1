package com.teamseven.config;

import com.teamseven.api.ApiResponseEnvelope;
import com.teamseven.api.ApiRestControllerAdvice;
import com.teamseven.common.LocalExceptionProperty;
import com.teamseven.exceptions.ApiError;
import com.teamseven.exceptions.ApiException;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.exceptions.BadRequestApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class AzuolasControllerAdvice extends ApiRestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AzuolasControllerAdvice.class);

    private final MessageSource messageSource;

    private final String DEFAULT_ERROR_MESSAGE_CODE = "error.default";

    @Autowired
    public AzuolasControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException ex, WebRequest request) {
        Locale locale = request.getLocale();
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getFieldErrors()
                .stream()
                .map(objectError -> fetchMessageForFieldErrorOrDefault(objectError, locale, ex))
                .collect(Collectors.toList());

        for (String err : errorMessages) {
            ApiError ae = new ApiError(err, 44);
            envelope.addError(ae);
        }

        return new ResponseEntity(envelope, HttpStatus.BAD_REQUEST);
    }

    public String fetchMessageForFieldErrorOrDefault(FieldError fieldError, Locale locale, MethodArgumentNotValidException ex) {
        try {
            return messageSource.getMessage(fieldError.getDefaultMessage(), ex.getStackTrace(), locale);
        } catch (NoSuchMessageException e) {
            return messageSource.getMessage(fieldError, locale);
        }
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleOtherApiExceptions(Exception ex, WebRequest request) {
        ApiException apiException = (ApiException) ex;
        logger.error("An error occurred", ex);

        Object[] arguments = (apiException.getArgs() == null) ? getArgsFromInnerExceptionIfPresent(apiException.getCause()) : apiException.getArgs();
        ApiResponseEnvelope envelope = buildErrorResponse(apiException, arguments, request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Object[] getArgsFromInnerExceptionIfPresent(Throwable throwable) {
        if (throwable == null) return new Object[] {};
        if (throwable instanceof AzuolasServiceException)
            return ((AzuolasServiceException) throwable).getArgs();

        if (throwable instanceof ApiException)
            return ((ApiException) throwable).getArgs();
        return new Object[] {};
    }

    private ApiResponseEnvelope buildErrorResponse(Exception ex, Object[] args, WebRequest webRequest) {
        ApiResponseEnvelope envelope = new ApiResponseEnvelope();
        if (ex instanceof ApiException) {
            envelope.addError(this.isJavaCastingException(ex) ? messageSource.getMessage(DEFAULT_ERROR_MESSAGE_CODE, ex.getStackTrace(), webRequest.getLocale()) : fetchMessageForCodeOrDefault(ex, args, webRequest.getLocale()));
        }

        if (envelope.getErrors().size() > 0) {
            envelope.setSuccess(false);
        }

        return envelope;
    }

    public String fetchMessageForCodeOrDefault(Exception ex, Object[] args, Locale locale) {
        String msg;
        try {
            msg = messageSource.getMessage(ex.getMessage(), args, ex.getMessage(), locale);

        } catch (Exception e) {
            msg = messageSource.getMessage(DEFAULT_ERROR_MESSAGE_CODE, ex.getStackTrace(), locale);
            logger.error(ex.getMessage(), ex);
        }

        return msg;
    }

    @ExceptionHandler({BadRequestApiException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        logger.error("An error occurred", ex);
        BadRequestApiException badRequestApiException = (BadRequestApiException) ex;
        ApiResponseEnvelope envelope = buildErrorResponse(badRequestApiException, ((BadRequestApiException) ex).getArgs(), request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        Locale locale = request.getLocale();
        logger.error("An error occurred", ex);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        String errorMessage = getExceptionMessageForProperty(ex, new Object[] {}, LocalExceptionProperty.ACCESS_DENIED_EXCEPTION, locale);
        ApiError ae = new ApiError(errorMessage, 31);
        envelope.addError(ae);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.UNAUTHORIZED);
    }

    public String getExceptionMessageForProperty(Exception ex, Object[] args, LocalExceptionProperty exceptionProperty, Locale locale) {
        return messageSource.getMessage(exceptionProperty.getValue(), args, locale);
    }

    @ExceptionHandler({PersistenceException.class})
    public ResponseEntity<Object> handlePersistenceException(Exception ex, WebRequest request) {
        Locale locale = request.getLocale();
        logger.error("A Persistence Exception was thrown: ", ex);
        String errorMessage = getExceptionMessageForProperty(ex, new Object[] {}, LocalExceptionProperty.PERSISTENCE_EXCEPTION, locale);
        ApiError error = new ApiError(errorMessage, 0);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        envelope.addError(error);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpClientErrorException.class, ResourceAccessException.class})
    public ResponseEntity<Object> handleHttpClientException(Exception ex, WebRequest request) {
        logger.error("An API Exception was thrown: ", ex);
        Locale locale = request.getLocale();
        String errorMessage = getExceptionMessageForProperty(ex, new Object[] {}, LocalExceptionProperty.HTTP_CLIENT_EXCEPTION, locale);
        ApiError error = new ApiError(errorMessage, 0);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        envelope.addError(error);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AzuolasServiceException.class})
    public ResponseEntity<Object> handleCosmosServiceException(AzuolasServiceException ex, WebRequest request) {
        logger.error("An error occurred", ex);
        Locale locale = request.getLocale();
        String errorMessage = getExceptionMessageForProperty(ex, ex.getArgs(), LocalExceptionProperty.valueOf(ex.getMessage()), locale);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        ApiError apiError = new ApiError(ex.getMessage(), ex.getCode());
        envelope.setErrors(Collections.singletonList(apiError));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
