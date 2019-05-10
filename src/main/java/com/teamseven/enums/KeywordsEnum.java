package com.teamseven.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum KeywordsEnum {
    REPORT,
    ASSIGN,
    OFFICE,
    FAQ,
    COMPANY,
    APPLICANT,
    DEFAULT;

    public static Set<String> fromValue(String text) {
        for (KeywordsEnum type : KeywordsEnum.values()) {
            if (String.valueOf(type.toString()).equals(text)) {
                return getKeywords(type);
            }
        }
        return null;
    }

    private static Set<String> getKeywords(KeywordsEnum type){
        String[] keywords;

        switch (type){
            case ASSIGN:
                keywords = new String[]{};
                break;
            case OFFICE:
                keywords = new String[]{};
                break;
            case REPORT:
                keywords = new String[]{};
                break;
            default:
                keywords = new String[]{};
                break;
        }

        return new HashSet<>(Arrays.asList(keywords));
    }

}
