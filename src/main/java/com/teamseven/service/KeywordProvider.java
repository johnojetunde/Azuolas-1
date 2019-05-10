package com.teamseven.service;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.enums.KeywordsEnum;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeywordProvider {

    private static final Logger logger = LoggerFactory.getLogger(KeywordProvider.class);

    @Autowired
    private KeywordFactory keywordFactory;

    public List<AzuolasResponse> treatRequest(AzuolasRequest request)throws AzuolasServiceException{
        String requestQuery = request.getSearchString();
        request.setKeywordsEnums(extractKeywords(requestQuery));
        return processKeywords(request);
    }

    private Set<KeywordsEnum> extractKeywords(String queryString){
        Set<KeywordsEnum> keywordsEnums = new HashSet<>();

        Map<KeywordsEnum, Set<String>> enumKeys = new HashMap<>(); // Keyword Enums from db

        String words[] = queryString.split(" ");

        enumKeys.keySet().parallelStream().forEach(k->{

            Set<String> wordsList = new HashSet<>(Arrays.asList(words));
            Set<String> keywords = enumKeys.get(k);
            wordsList.retainAll(keywords);
            if (wordsList.size() > 1){
                keywordsEnums.add(k);
                return;
            }
        });



        return keywordsEnums;
    }


    private List<AzuolasResponse> processKeywords(AzuolasRequest request)throws AzuolasServiceException {
        List<KeywordProcessor> keywordProcessors =  new ArrayList<>();
        List<AzuolasResponse> azuolasResponses =  new ArrayList<>();

        if(request.getKeywordsEnums().isEmpty()){

        keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.FAQ));
        }

//
//        if(request.getKeywordsEnums().contains(KeywordsEnum.REPORT)){
//            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.REPORT));
//        }
//        if(request.getKeywordsEnums().contains(KeywordsEnum.OFFICE)){
//            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.OFFICE));
//        }
//
//        if(request.getKeywordsEnums().isEmpty()){
//            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.DEFAULT));
//        }



        keywordProcessors.parallelStream().forEach(p->{
            try {
                azuolasResponses.add(p.process(request));
            }catch(AzuolasServiceException e){
                logger.error("Error getting the provider ",e.getMessage());
            }
        });

        return azuolasResponses;

    }
}
