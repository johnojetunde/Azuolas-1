package com.teamseven.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AzuolasResponse implements Serializable {

    @JsonProperty
    private String message;

    @JsonProperty
    private boolean hasFile;

    @JsonProperty
    private String fileData;

    @JsonProperty
    private String fileName;

    public boolean isHasFile() { return hasFile; }

    public void setHasFile(boolean hasFile) { this.hasFile = hasFile; }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getFileData() { return fileData; }

    public void setFileData(String fileData) { this.fileData = fileData; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }
}
