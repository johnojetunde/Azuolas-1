package com.teamseven.service.keywordprocessor.impl;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.stereotype.Service;

@Service
public class ReportKeywordImpl implements KeywordProcessor {

    @Override
    public AzuolasResponse process(AzuolasRequest request){
        return new AzuolasResponse();
    }
}
