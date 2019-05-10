package com.teamseven.controller;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.service.KeywordProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/azuolas")
public class BotInquireController {
    private static final Logger logger = LoggerFactory.getLogger(BotInquireController.class);

    @Autowired
    private KeywordProvider keywordProvider;


    @RequestMapping(value = "/inquire", method = RequestMethod.POST)
    public List<AzuolasResponse> inquire(@RequestBody @Validated AzuolasRequest request){
        try {
            return keywordProvider.treatRequest(request);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

}
