package com.teamseven.exceptions;

public class AzuolasServiceException extends AzuolasException {

    private Integer code;

    public AzuolasServiceException() {
        this.code = -1;
    }

    public AzuolasServiceException(String message, Throwable inner) {
        super(message, inner);
        this.code = -1;
    }

    public AzuolasServiceException(String message) {
        super(message);
        this.code = -1;
    }

    public AzuolasServiceException(Throwable inner) {
        super(inner.getMessage(), inner);
        this.code = -1;
    }

    public AzuolasServiceException(Integer code, String message) {
        this(message);
        this.code = code;
    }

    public AzuolasServiceException(Integer code, Throwable inner) {
        this(inner);
        this.code = code;
    }

    public AzuolasServiceException(int code, String message, Throwable inner) {
        this(message, inner);
        this.code = code;
    }

    public AzuolasServiceException(String message, Object[] args) {
        super(message, args);
        this.code = -1;
    }

    public Integer getCode() {
        return this.code;
    }
}
