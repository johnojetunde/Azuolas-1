package com.teamseven.api;


import com.teamseven.exceptions.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ApiRestControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = Logger.getLogger(ApiRestControllerAdvice.class.getName());
    private static final String DEFAULT_ERROR_RESPONSE = "Unable to complete request at this time";

    public ApiRestControllerAdvice() {
    }

    @ExceptionHandler({NotFoundApiException.class})
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.NOT_FOUND);
    }

    protected ApiResponseEnvelope buildErrorResponse(Exception ex) {
        ApiResponseEnvelope envelope = new ApiResponseEnvelope();
        if (ex instanceof ApiException) {
            envelope.addError(this.isJavaCastingException(ex) ? "Unable to complete request at this time" : ex.getMessage());
        }

        if (envelope.getErrors().size() > 0) {
            envelope.setSuccess(false);
        }

        return envelope;
    }

    protected boolean isJavaCastingException(Exception ex) {
        String message = ex.getMessage();
        return message == null || message.contains("com.") || message.contains("org.") || message.contains("java") || message.equals("null") || message.contains("javax");
    }

    @ExceptionHandler({ForbiddenApiException.class})
    public ResponseEntity<Object> handleForbidden(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UnauthorizedApiException.class})
    public ResponseEntity<Object> handleUnauthorized(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({BadRequestApiException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ErrorApiException.class})
    public ResponseEntity<Object> handleError(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleOtherApiExceptions(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = this.buildErrorResponse(ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        Iterator var6 = errors.iterator();

        while(var6.hasNext()) {
            FieldError err = (FieldError)var6.next();
            ApiError ae = new ApiError(err.getField() + " " + err.getDefaultMessage(), 44);
            envelope.addError(ae);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        ApiError ae = new ApiError("Access denied", 31);
        envelope.addError(ae);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AzuolasServiceException.class})
    public ResponseEntity<Object> handleCosmosServiceException(AzuolasServiceException ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        ApiError apiError = new ApiError(ex.getMessage(), ex.getCode());
        envelope.setErrors(Collections.singletonList(apiError));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericErrors(Exception ex, WebRequest request) {
        logger.log(Level.SEVERE, "An error occurred", ex);
        ApiResponseEnvelope envelope = new ApiResponseEnvelope(false);
        envelope.setErrors(this.getErrorsFromException(ex));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(envelope, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected List<ApiError> getErrorsFromException(Exception ex) {
        List<ApiError> errors = new ArrayList();
        ApiError err = new ApiError(this.isJavaCastingException(ex) ? "Unable to process request" : ex.getMessage(), 0);
        errors.add(err);
        return errors;
    }

    public boolean supports(MethodParameter mp, Class<? extends HttpMessageConverter<?>> type) {
        return true;
    }

    public Object beforeBodyWrite(Object body, MethodParameter mp, MediaType mt, Class<? extends HttpMessageConverter<?>> type, ServerHttpRequest shr, ServerHttpResponse shr1) {
        return body instanceof ApiResponseEnvelope ? this.cleanXSSObjectFields(body) : new ApiResponseEnvelope(true, this.cleanXSSObjectFields(body));
    }

    protected Object cleanXSSObjectFields(Object body) {
        if (body == null) {
            return null;
        } else {
            Field[] fields = body.getClass().getDeclaredFields();
            Field[] var3 = fields;
            int var4 = fields.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field f = var3[var5];
                f.setAccessible(true);

                try {
                    Object value = f.get(body);
                    if (value != null && value instanceof String && !((String)value).isEmpty()) {
                        Object cleanValue = this.cleanXSS((String)value);
                        f.set(body, cleanValue);
                    }
                } catch (IllegalAccessException var9) {
                    logger.log(Level.SEVERE, var9.getMessage(), var9);
                }
            }

            return body;
        }
    }

    private String cleanXSS(String value) {
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        return value;
    }
}
