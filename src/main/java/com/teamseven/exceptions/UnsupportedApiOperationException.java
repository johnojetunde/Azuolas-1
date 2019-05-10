package com.teamseven.exceptions;

public class UnsupportedApiOperationException  extends ApiException{

    public UnsupportedApiOperationException(String message) {
        super(message);
    }

    public UnsupportedApiOperationException(Throwable ex) {
        super(ex);
    }

    public UnsupportedApiOperationException(Throwable ex, int code) {
        super(ex, code);
    }

    public UnsupportedApiOperationException(String message, int code) {
        super(message, code);
    }

}
