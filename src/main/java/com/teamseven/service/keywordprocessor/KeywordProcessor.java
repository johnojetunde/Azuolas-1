package com.teamseven.service.keywordprocessor;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;

public interface KeywordProcessor {

    AzuolasResponse process(AzuolasRequest request) throws AzuolasServiceException;
}
