package com.teamseven.service.processor;

import com.teamseven.service.KeywordProcessor;
import org.springframework.stereotype.Service;

@Service
public class ReportKeywordImpl implements KeywordProcessor {

    @Override
    public Object process(Object object){
        return "String";
    }
}
