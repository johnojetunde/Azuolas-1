package com.teamseven.exceptions;

public class AzuolasDatabaseException extends RuntimeException {

    public AzuolasDatabaseException() {
        super();
    }

    public AzuolasDatabaseException(String message, Throwable inner) {
        super(message, inner);
    }

    public AzuolasDatabaseException(Throwable inner) {
        super(null, inner);
    }

    public AzuolasDatabaseException(String message) {
        super(message);
    }

}
