package com.teamseven.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CognitiveResponse{

    private List<Object> answers;

    public List<Object> getCoginitiveAnswerList() {
        return answers;
    }
    public void setCoginitiveAnswerList(List<Object> answers) {
        this.answers = answers;
    }
}
