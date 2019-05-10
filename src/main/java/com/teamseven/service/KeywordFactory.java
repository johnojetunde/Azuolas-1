package com.teamseven.service;

import com.teamseven.enums.KeywordsEnum;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import com.teamseven.service.keywordprocessor.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordFactory {

    @Autowired
    private ReportKeywordImpl reportKeyword;
    @Autowired
    private FAQKeywordImpl faqKeyword;
    @Autowired
    private CompanyKeywordImpl companyKeyword;
    @Autowired
    private DefaultKeywordImpl defaultKeyword;
    @Autowired
    private ApplicantKeywordImpl applicantKeyword;



    public KeywordProcessor getProcessor(KeywordsEnum keywordsEnum){
        switch(keywordsEnum){
            case REPORT: return reportKeyword;
            case FAQ: return faqKeyword;
            case COMPANY: return companyKeyword;
            case APPLICANT: return applicantKeyword;
            default: return defaultKeyword;
        }
    }

}
