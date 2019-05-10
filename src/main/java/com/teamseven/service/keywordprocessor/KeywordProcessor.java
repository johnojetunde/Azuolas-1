package com.teamseven.service.keywordprocessor;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;

public interface KeywordProcessor {

    AzuolasResponse process(AzuolasRequest request);
}
