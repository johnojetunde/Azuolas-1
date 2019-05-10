package com.teamseven.service.keywordprocessor.impl;

import com.teamseven.apimodel.AzuolasRequest;
import com.teamseven.apimodel.AzuolasResponse;
import com.teamseven.exceptions.AzuolasServiceException;
import com.teamseven.model.Applicant;
import com.teamseven.repository.ApplicantRepostory;
import com.teamseven.service.keywordprocessor.ApplicationExcelProcessor;
import com.teamseven.service.keywordprocessor.KeywordProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompanyKeywordImpl implements KeywordProcessor {

    @Autowired
    private ApplicantRepostory applicantRepostory;

    @Autowired
    private FAQKeywordImpl faqKeyword;

    @Autowired
    private ApplicationExcelProcessor applicationExcelProcessor;

    @Override
    public AzuolasResponse process(AzuolasRequest request)throws AzuolasServiceException{
        AzuolasResponse response =  new AzuolasResponse();
        try{

            String searchString = request.getSearchString().toLowerCase();

            if(searchString.contains("list of") && searchString.contains("developers-with") && searchString.contains("years of experience") ){
                searchString = searchString.replaceAll("list of", "");
                searchString = searchString.replaceAll("developers-with", "");
                searchString = searchString.replaceAll("years of experience", "");

                searchString = searchString.trim();

                Long yearsOfExperience = 0L;
                try {
                    yearsOfExperience = Long.parseLong(searchString);
                } catch (NumberFormatException e) {
                    throw new AzuolasServiceException("Error understanding user input");
                }

                List<Applicant> applicantList = applicantRepostory.findAllByYearsOfExperience(yearsOfExperience);

                if(!applicantList.isEmpty()) {

                    response = generateExcel(applicantList);
                }else{
                    response.setHasFile(false);
                    response.setMessage("No matching applicant");
                }

                return response;

            }else if(searchString.contains("list of") && searchString.contains("developers-above") && searchString.contains("years old") ){
                searchString = searchString.replaceAll("list of", "");
                searchString = searchString.replaceAll("developers-above", "");
                searchString = searchString.replaceAll("years old", "");

                searchString = searchString.trim();

                Long age = 0L;
                try {
                    age = Long.parseLong(searchString);
                } catch (NumberFormatException e) {
                    throw new AzuolasServiceException("Error understanding user input");
                }

                LocalDate localDate =  LocalDate.now();
                localDate = localDate.minusYears(age);

                Date date =  Date.valueOf(localDate);
                List<Applicant> applicantList = applicantRepostory.findAllByDateOfBirthLessThanEqual(date);

                if(!applicantList.isEmpty()) {

                    response = generateExcel(applicantList);
                }else{
                    response.setHasFile(false);
                    response.setMessage("No matching applicant");
                }

                return response;

            }
            else if(searchString.contains("list of") && searchString.contains("developers-below") && searchString.contains("years old") ){
                searchString = searchString.replaceAll("list of", "");
                searchString = searchString.replaceAll("developers-below", "");
                searchString = searchString.replaceAll("years old", "");

                searchString = searchString.trim();

                Long age = 0L;
                try {
                    age = Long.parseLong(searchString);
                } catch (NumberFormatException e) {
                    throw new AzuolasServiceException("Error understanding user input");
                }

                LocalDate localDate =  LocalDate.now();
                localDate = localDate.minusYears(age);

                Date date =  Date.valueOf(localDate);
                List<Applicant> applicantList = applicantRepostory.findAllByDateOfBirthGreaterThanEqual(date);

                if(!applicantList.isEmpty()) {

                    response = generateExcel(applicantList);
                }else{
                    response.setHasFile(false);
                    response.setMessage("No matching applicant");
                }

                return response;

            }
            else if(searchString.contains("list of") && searchString.contains("developer")) {

                searchString = searchString.replaceAll("list of", "");
                searchString = searchString.replaceAll("developers", "");
                searchString = searchString.replaceAll("developer", "");

                List<Applicant> applicantList = applicantRepostory.findAll();

                List<String> stacks = Arrays.asList(searchString.trim().split(","));


                List<Applicant> filteredApplicants = new ArrayList<>();

                applicantList.forEach(a -> {
                    if (hasStack(a, stacks)) {
                        filteredApplicants.add(a);
                    }
                });

                if(!filteredApplicants.isEmpty()) {

                    String fileData = applicationExcelProcessor.generateReport(filteredApplicants);

                    response.setMessage("Your request has been successfully processed. See below the link to download your report");
                    response.setHasFile(true);
                    response.setFileData(fileData);
                    response.setFileName("Digital Explorers Export");
                }else{
                    response.setHasFile(false);
                    response.setMessage("No matching applicant");
                }

                return response;
            }else{
                return faqKeyword.process(request);
            }


        }catch(IOException err){
            throw new AzuolasServiceException("Error generating report",err);
        }

    }




    private boolean hasStack(Applicant a,List<String> stacks){
        for(String stack: stacks){
            if(a.getStack().toLowerCase().contains(stack.trim())){
                return true;
            }
        }
        return false;
    }


    private AzuolasResponse generateExcel(List<Applicant> filteredApplicants)throws IOException{
        AzuolasResponse response =  new AzuolasResponse();
        String fileData = applicationExcelProcessor.generateReport(filteredApplicants);

        response.setMessage("Your request has been successfully processed. See below the link to download your report");
        response.setHasFile(true);
        response.setFileData(fileData);
        response.setFileName("Digital Explorers Export");

        return response;
    }





}
