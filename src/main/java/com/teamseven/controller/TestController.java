package com.teamseven.controller;

import com.teamseven.apimodel.SampleModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @RequestMapping("/user")
    public String test(){
        return "User xis here";
    }

    @RequestMapping(path = "/inquire", produces = MediaType.APPLICATION_JSON_VALUE)
    public SampleModel inquire() {
        SampleModel sampleModel =  new SampleModel();
        sampleModel.setAddress("Quarter to the address");
        sampleModel.setName("Quarer to the name of the sample apimodel");

        return sampleModel;
    }


}
