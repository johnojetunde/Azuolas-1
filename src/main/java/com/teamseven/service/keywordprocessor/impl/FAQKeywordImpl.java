package com.teamseven.service.keywordprocessor.impl;


import com.google.common.base.Strings;
import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class FAQKeywordImpl implements KeywordProcessor {

    @Value("${azuolas.congitive.url:https://azuolas.azurewebsites.net/qnamaker/knowledgebases/abbb48d9-5fa4-47fc-a14d-f9e1a6e664b1/generateAnswer}")
    private String endPointurl;

    @Value("${azuolas.congitive.authorization:EndpointKey 47841dce-b7bd-480c-8116-1aacac0113ae}")
    private String endPointKey;

    @Override
    public AzuolasResponse process(AzuolasRequest request)throws AzuolasServiceException {
        try {

            HashMap parameters = new HashMap<>();
            parameters.put("question", request.getSearchString());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization",endPointKey);

            HttpEntity<HashMap> entityRequest = new HttpEntity<>(parameters, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Object> responseEntity
                    = restTemplate.postForEntity(endPointurl.trim(), entityRequest, Object.class);

            return extractAnswer(responseEntity.getBody());
        }catch(Exception e){
            throw new AzuolasServiceException(e.getMessage());
        }
    }


    private AzuolasResponse extractAnswer(Object apiResponse)throws AzuolasServiceException{
        try {
            AzuolasResponse azuolasResponse;
            LinkedHashMap<String, ArrayList<LinkedHashMap<String,String>>> responseFormatted = (LinkedHashMap) apiResponse;

            List<LinkedHashMap<String,String>> responseAnswer =  responseFormatted.get("answers");

            List<String> answers = new ArrayList<>();
            responseAnswer.parallelStream().forEach(a -> {
                answers.add(a.get("answer"));
            });

            azuolasResponse = new AzuolasResponse();
            azuolasResponse.setMessage(String.join("\n\n\n\n",answers));
            azuolasResponse.setHasFile(false);

            return azuolasResponse;
        }catch(Exception e){
            throw new AzuolasServiceException("error extracting answer from cognitive end point",e);
        }
    }
}
