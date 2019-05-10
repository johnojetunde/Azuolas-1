package com.teamseven.apimodel;

import com.teamseven.enums.KeywordsEnum;

import java.util.Set;

public class AzuolasRequest {

    private String searchString;

    private String userEmail;

    private Set<KeywordsEnum> keywordsEnums;

    public Set<KeywordsEnum> getKeywordsEnums() {
        return keywordsEnums;
    }

    public void setKeywordsEnums(Set<KeywordsEnum> keywordsEnums) {
        this.keywordsEnums = keywordsEnums;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
