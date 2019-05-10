package com.teamseven.apimodel;

import com.teamseven.enums.KeywordsEnum;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class AzuolasRequest {

    @NotEmpty(message = "Azuolas needs to know what's on your mind")
    @NotNull(message = "Azuolas needs to know what's on your mind")
    private String searchString;

    private String userEmail;

    private String userType;

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

    public String getUserType() { return userType; }

    public void setUserType(String userType) { this.userType = userType; }
}
