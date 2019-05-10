package com.teamseven.exceptions;

public class BadRequestApiException extends ApiException {

    public BadRequestApiException(String message, int code) {
        super(message, code);
    }

    public BadRequestApiException(Throwable cause, int code) {
        super(cause, code);
    }

    public BadRequestApiException(String message) {
        super(message);
    }

    public BadRequestApiException(Throwable cause) {
        super(cause);
    }

}
