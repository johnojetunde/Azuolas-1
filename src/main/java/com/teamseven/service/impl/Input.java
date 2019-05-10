package com.teamseven.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Input {

    Set<String> wordList;

    public Input(String input){
        input = input.toLowerCase();
        String words[] = input.split(" ");
//        wordList = new Set<>(Arrays.asList(words));
    }

}
