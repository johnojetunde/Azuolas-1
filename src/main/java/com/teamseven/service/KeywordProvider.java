package com.teamseven.service;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.enums.KeywordsEnum;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeywordProvider {

    @Autowired
    private KeywordFactory keywordFactory;

    public List<AzuolasResponse> treatRequest(AzuolasRequest request){
        String requestQuery = request.getSearchString();
        request.setKeywordsEnums(extractKeywords(requestQuery));
        return processKeywords(request);
    }

    private Set<KeywordsEnum> extractKeywords(String queryString){
        Set<KeywordsEnum> keywordsEnums = new HashSet<>();

        HashMap<KeywordsEnum, Set<String>> enumKeys = new HashMap<>(); // Keyword Enums from db

        String words[] = queryString.split(" ");

        for (KeywordsEnum keyword : enumKeys.keySet()){
            Set<String> wordsList = new HashSet<>(Arrays.asList(words));
            Set<String> keywords = enumKeys.get(keyword);
            wordsList.retainAll(keywords);
            if (wordsList.size() > 1){
                keywordsEnums.add(keyword);
                break;
            }
        }

        return keywordsEnums;
    }


    private List<AzuolasResponse> processKeywords(AzuolasRequest request){
        List<KeywordProcessor> keywordProcessors =  new ArrayList<>();

        List<AzuolasResponse> azuolasResponses =  new ArrayList<>();
        if(request.getKeywordsEnums().contains(KeywordsEnum.REPORT)){
            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.REPORT));
        }
        if(request.getKeywordsEnums().contains(KeywordsEnum.OFFICE)){
            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.OFFICE));
        }

        if(request.getKeywordsEnums().isEmpty()){
            keywordProcessors.add(keywordFactory.getProcessor(KeywordsEnum.DEFAULT));
        }


        keywordProcessors.parallelStream().forEach(p->{
            azuolasResponses.add(p.process(request));
        });

        return azuolasResponses;

    }
}
