package com.teamseven.service;

import com.teamseven.enums.KeywordsEnum;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import com.teamseven.service.keywordprocessor.impl.DefaultKeywordImpl;
import com.teamseven.service.keywordprocessor.impl.FAQKeywordImpl;
import com.teamseven.service.keywordprocessor.impl.ReportKeywordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordFactory {

    @Autowired
    private ReportKeywordImpl reportKeyword;
    @Autowired
    private FAQKeywordImpl faqKeyword;
    @Autowired
    private DefaultKeywordImpl defaultKeyword;



    public KeywordProcessor getProcessor(KeywordsEnum keywordsEnum){
        switch(keywordsEnum){
            case REPORT: return reportKeyword;
            case FAQ: return faqKeyword;
            default: return defaultKeyword;
        }
    }

}
