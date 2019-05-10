package com.teamseven.exceptions;

public class ForbiddenApiException extends ApiException {

    public ForbiddenApiException(String message) {
        super(message);
    }

    public ForbiddenApiException(Throwable ex) {
        super(ex);
    }

    public ForbiddenApiException(String message, int code) {
        super(message, code);
    }

    public ForbiddenApiException(Throwable cause, int code) {
        super(cause, code);
    }

}
