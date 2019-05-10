package com.teamseven.enums;

public enum KeywordsEnum {
    REPORT,
    ASSIGN,
    OFFICE,
    DEFAULT;

    public static KeywordsEnum fromValue(String text) {
        for (KeywordsEnum type : KeywordsEnum.values()) {
            if (String.valueOf(type.toString()).equals(text)) {
                return type;
            }
        }
        return null;
    }

}
