package com.teamseven.service;

import com.teamseven.service.impl.Keyword;
import com.teamseven.service.processor.ReportKeywordImpl;
import org.springframework.beans.factory.annotation.Autowired;

import static com.teamseven.service.KeywordsEnum.*;

public class KeywordFactory {

    @Autowired
    private ReportKeywordImpl reportKeyword;

    public void process(KeywordsEnum keywordsEnum){

        getProcessor(keywordsEnum).process("string");


    }

    public KeywordProcessor getProcessor(KeywordsEnum keywordsEnum){
        switch(keywordsEnum){
            case REPORT: return reportKeyword;

            default: return reportKeyword;
        }
    }
}
