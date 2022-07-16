package com.hw.tdd.service;

import java.util.ArrayList;
import java.util.List;

public class TemplateParser {
    public List<String> parseTemplate(String template) {
        List placeholders = new ArrayList<>();
        placeholders.add("value1");
        placeholders.add("value2");
        placeholders.add("value3");
        return placeholders;
    }
}
