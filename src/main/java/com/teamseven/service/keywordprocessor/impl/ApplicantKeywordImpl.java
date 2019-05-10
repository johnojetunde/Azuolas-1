package com.teamseven.service.keywordprocessor.impl;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.model.ApplicantStatus;
import com.teamseven.repository.ApplicantStatusRepository;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantKeywordImpl implements KeywordProcessor {

    @Autowired
    private ApplicantStatusRepository applicantStatusRepository;

    @Autowired
    private FAQKeywordImpl faqKeyword;

    @Override
    public AzuolasResponse process(AzuolasRequest request) throws AzuolasServiceException {

        AzuolasResponse response = new AzuolasResponse();
        try {

            String searchString = request.getSearchString().toLowerCase();

            if (searchString.contains("status") && searchString.contains("application")) {

                ApplicantStatus status = applicantStatusRepository.findByApplicant_Email(request.getUserEmail());

                if(status != null) {
                    String message = "Your application status is: " + status.getStatus().getStatusName();
                    response.setHasFile(false);
                    response.setMessage(message);
                }else{
                    response.setHasFile(false);
                    response.setMessage("You do not have an active application at the moment");
                }

                return response;

            }  else {
                return faqKeyword.process(request);
            }


        } catch (Exception err) {
            throw new AzuolasServiceException(err.getMessage());
        }
    }

}
