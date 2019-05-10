package com.teamseven.service.keywordprocessor.impl;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.stereotype.Service;

@Service
public class DefaultKeywordImpl implements KeywordProcessor {

    @Override
    public AzuolasResponse process(AzuolasRequest request)throws AzuolasServiceException{
        throw new AzuolasServiceException("We do not have an implementation for this keyword");
    }
}
