package com.teamseven.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teamseven.exceptions.ApiError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApiResponseEnvelope {

    private boolean success;
    private Object result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiError> errors = new ArrayList();

    public ApiResponseEnvelope() {
    }

    public ApiResponseEnvelope(boolean success) {
        this.success = success;
    }

    public ApiResponseEnvelope(Object result) {
        this.result = result;
        this.success = true;
    }

    public ApiResponseEnvelope(boolean success, Object result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void addError(ApiError error) {
        this.errors.add(new ApiError(this.cleanXSS(error.getMessage()), error.getCode()));
    }

    private String cleanXSS(String value) {
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        value = value.replaceAll("<script>", "");
        value = value.replaceAll("</script>", "");
        return value;
    }

    public void addError(String error) {
        this.errors.add(new ApiError(this.cleanXSS(error), 0));
    }

    public List<ApiError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ApiError> errs) {
        this.errors = new ArrayList();
        Iterator var2 = errs.iterator();

        while(var2.hasNext()) {
            ApiError e = (ApiError)var2.next();
            this.errors.add(new ApiError(this.cleanXSS(e.getMessage()), e.getCode()));
        }

    }
}
