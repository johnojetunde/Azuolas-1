package com.teamseven.exceptions;

public class NotFoundApiException extends ApiException {

    public NotFoundApiException(String message, int code) {
        super(message, code);
    }

    public NotFoundApiException(Throwable cause, int code) {
        super(cause, code);
    }

    public NotFoundApiException(String message) {
        super(message);
    }

    public NotFoundApiException(Throwable cause) {
        super(cause);
    }

}
