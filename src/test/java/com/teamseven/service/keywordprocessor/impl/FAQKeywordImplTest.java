package com.teamseven.service.keywordprocessor.impl;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FAQKeywordImplTest {

    @Autowired
    private FAQKeywordImpl defaultKeyword;

    @Test
    public void process() {
        try {
            AzuolasRequest request = new AzuolasRequest();
            request.setSearchString("digital explorer");

            AzuolasResponse response = defaultKeyword.process(request);
            assertNotNull(response);
        }catch(AzuolasServiceException e){

        }
    }
}