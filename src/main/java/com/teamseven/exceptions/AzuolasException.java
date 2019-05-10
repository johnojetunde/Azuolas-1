package com.teamseven.exceptions;

public class AzuolasException extends Exception {

    protected Object[] args;
    public AzuolasException() {
    }

    public AzuolasException(String message, Throwable inner) {
        super(message, inner);
    }

    public AzuolasException(String message) {
        super(message);
    }

    public AzuolasException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
