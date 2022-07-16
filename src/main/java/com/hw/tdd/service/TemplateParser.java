package com.hw.tdd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    private static final String REGEX = "\\#\\{(?<placeholder>[A-Za-z0-9-_]+)}";

    public List<String> parseTemplate(String template) {
        List<String> placeholders = new ArrayList<>();

        Pattern regex = Pattern.compile(REGEX);
        Matcher regexMatcher = regex.matcher(template);

        while (regexMatcher.find()) {
            placeholders.add(regexMatcher.group(1));
        }
        return placeholders;
    }

    public String replacePlaceholders(String template, Map<String, String> placeholderValues) {
        String result = template;
        for (Map.Entry<String, String> entry: placeholderValues.entrySet()) {
            result = result.replace("#{" + entry.getKey() + "}", "#{" + entry.getValue()+ "}");
        }
        return result;
    }
}
