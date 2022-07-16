package com.hw.tdd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TemplateParserTest {

    private static final String TEMPLATE = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";

    @Test
    public void getPlaceholdersFromTheTemplate() {
        TemplateParser parser = new TemplateParser();
        List<String> placeholders = parser.parseTemplate(TEMPLATE);

        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders));
    }

}
