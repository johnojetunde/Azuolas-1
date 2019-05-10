package com.teamseven.exceptions;

public class ApiError {

    public static final int ERR_GENERIC = 00;   // Generic error code

    public static final int ERR_AUTHENTICATION_FAILED = 30; // Could not authenticate user
    public static final int ERR_AUTHORIZATION_FAILED = 31;
    /**
     * When a request is made for a particular resource that doesn't exist
     */
    public static final int ERR_ENTITY_NOT_FOUND = 40;
    /**
     * When a related entity for a request does not exist,
     * e.g a request to validate an account number when the account does not exist
     */
    public static final int ERR_RELATED_ENTITY_NOT_FOUND = 41;
    public static final int ERR_PARENT_ENTITY_NOT_FOUND = 42;

    public static final int ERR_DUPLICATE_ENTRY = 43;

    public static final int ERR_INVALID_DATA = 44;


    // SERVER ERRORS
    /**
     * When there was an error making a database operation
     */
    public static final int ERR_DATABASE = 50;
    /**
     * When there was an io error e.g saving a file
     */
    public static final int ERR_IO = 51;

    /**
     * When service to service calls fail
     */
    public static final int ERR_ENDPOINT_DOWN = 60;

    private String message;
    private int code;

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
