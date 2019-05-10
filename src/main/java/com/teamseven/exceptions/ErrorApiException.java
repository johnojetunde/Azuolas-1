package com.teamseven.exceptions;

public class ErrorApiException extends ApiException {

    public ErrorApiException(String message, int code) {
        super(message, code);
    }

    public ErrorApiException(Throwable cause, int code) {
        super(cause, code);
    }

    public ErrorApiException(String message) {
        super(message);
    }

    public ErrorApiException(Throwable ex) {
        super(ex);
    }

}
