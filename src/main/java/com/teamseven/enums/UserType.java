package com.teamseven.enums;

public enum UserType {
    COMPANY,
    APPLICANT,
    DETEAM;


    public static UserType fromValue(String text) {
        for (UserType type : UserType.values()) {
            if (String.valueOf(type.toString()).equals(text)) {
                return type;
            }
        }
        return null;
    }
}
