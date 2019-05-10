package com.teamseven.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserInput {

    private Set<String> keywords;

    public UserInput(String input) {
        input = input.toLowerCase();
        String[] words = input.split(" ");

        keywords = new HashSet<>(Arrays.asList(words));
        keywords.retainAll(new Keywords().getAll());
    }
}
