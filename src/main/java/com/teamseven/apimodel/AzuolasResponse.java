package com.teamseven.apimodel;

import java.util.List;
import java.util.Map;

public class AzuolasResponse {

    private List<String> message;

    private boolean hasFile;

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
