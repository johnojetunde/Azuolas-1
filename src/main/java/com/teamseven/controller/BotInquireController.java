package com.teamseven.controller;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.ApiException;
import com.teamseven.service.KeywordProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/azuolas")
public class BotInquireController {
    private static final Logger logger = LoggerFactory.getLogger(BotInquireController.class);

    @Autowired
    private KeywordProvider keywordProvider;


    @ResponseBody
    @RequestMapping(value = "/inquire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AzuolasResponse> inquire(@RequestBody @Validated AzuolasRequest request)throws ApiException {
        try {
            List<AzuolasResponse> response = keywordProvider.treatRequest(request);
            return response;
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }

}
