package com.hw.tdd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TemplateParserTest {

    private static final String TEMPLATE1 = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";
    private static final String TEMPLATE2 = "Some text input 1: #{value21}, Some text input 2: #{value22}.";

    @Test
    public void getPlaceholdersFromTheTemplate() {
        TemplateParser parser = new TemplateParser();

        List<String> placeholders1 = parser.parseTemplate(TEMPLATE1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders1));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders1, Arrays.asList("value1", "value2", "value3")));

        List<String> placeholders2 = parser.parseTemplate(TEMPLATE2);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders2));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders2, Arrays.asList("value21", "value22")));
    }

}
