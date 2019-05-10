package com.teamseven.exceptions;

public class AzuolasRuntimeException extends RuntimeException {
    public AzuolasRuntimeException() {
        super();
    }

    public AzuolasRuntimeException(String message, Throwable inner) {
        super(message, inner);
    }

    public AzuolasRuntimeException(String message) {
        super(message);
    }

}
