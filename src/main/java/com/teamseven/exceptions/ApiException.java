package com.teamseven.exceptions;

public class ApiException extends Exception {
    private static final String UNKNOWN_ERROR_MSG = "An unknown error has occured";
    protected Object[] args;

    public ApiException(String message, int code) {
        super(message);
    }

    public ApiException(Throwable cause, int code) {
        super(cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        this((Throwable)cause, 0);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public String getMessage() {
        Throwable cause = this.getCause();
        if (cause != null && cause instanceof AzuolasServiceException) {
            return cause.getMessage();
        } else {
            return super.getMessage() != null && !super.getMessage().isEmpty() ? super.getMessage() : null;
        }
    }

    public Object[] getArgs() {
        return this.args;
    }

    public static ApiException getUnknownException() {
        return new ApiException("An unknown error has occured");
    }

    public static ApiException getUnknownException(Throwable t) {
        t.printStackTrace();
        ApiException ex = new ApiException("An unknown error has occured");
        return ex;
    }
}
