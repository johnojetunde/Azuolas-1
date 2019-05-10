package com.teamseven.exceptions;

public class UnauthorizedApiException extends ApiException {

    public UnauthorizedApiException(String message) {
        super(message);
    }

    public UnauthorizedApiException(Throwable ex) {
        super(ex);
    }

    public UnauthorizedApiException(Throwable ex, int code) {
        super(ex, code);
    }

    public UnauthorizedApiException(String message, int code) {
        super(message, code);
    }

}
