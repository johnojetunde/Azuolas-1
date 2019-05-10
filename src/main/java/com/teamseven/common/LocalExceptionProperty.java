package com.teamseven.common;

public enum LocalExceptionProperty {

    PERSISTENCE_EXCEPTION ("database.persistence.exception"),
    HTTP_CLIENT_EXCEPTION("http.client.exception"),
    ACCESS_DENIED_EXCEPTION("access.denied");

    private String value;

    LocalExceptionProperty(String propertyName) {
        this.value = propertyName;
    }

    public String getValue() {
        return value;
    }
}
