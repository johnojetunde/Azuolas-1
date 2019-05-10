package com.teamseven.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AzuolasResponse implements Serializable {

    @JsonProperty
    private List<String> message;

    @JsonProperty
    private boolean hasFile;

    @JsonProperty
    private Map<String,String> fileData;

    public boolean isHasFile() { return hasFile; }

    public void setHasFile(boolean hasFile) { this.hasFile = hasFile; }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Map<String, String> getFileData() {
        return fileData;
    }

    public void setFileData(Map<String, String> fileData) {
        this.fileData = fileData;
    }
}
