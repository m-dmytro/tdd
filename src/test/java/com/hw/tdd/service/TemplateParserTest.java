package com.hw.tdd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TemplateParserTest {

    private static final String VALID_TEMPLATE_1 = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";
    private static final String VALID_TEMPLATE_2 = "Some text input 1: #{value21}, Some text input 2: #{value22}.";
    private static final String VALID_TEMPLATE_3 = "Some text input 1: #{value31}, Some text input 2: #{value32}, Some text input 3: #{value32}.";
    private static final String INVALID_TEMPLATE_1 = "Some text input 1: #{{value11}, Some text input 2: #{value12}.";
    private static final String INVALID_TEMPLATE_2 = "Some text input 1: {value21}, Some text input 2: #{value22}.";
    private static final String WITHOUT_PLACEHOLDERS_TEMPLATE = "Some text input 1: {value1}, Some text input 2: value2.";

    @Test
    public void getPlaceholdersFromTemplate_valid_returnAllPlaceholders() {
        TemplateParser parser = new TemplateParser();

        List<String> placeholders1 = parser.parseTemplate(VALID_TEMPLATE_1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders1));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders1, Arrays.asList("value1", "value2", "value3")));

        List<String> placeholders2 = parser.parseTemplate(VALID_TEMPLATE_2);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders2));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders2, Arrays.asList("value21", "value22")));

        List<String> placeholders3 = parser.parseTemplate(VALID_TEMPLATE_3);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders3));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders3, Arrays.asList("value31", "value32")));
    }

    @Test
    public void getPlaceholdersFromTemplate_invalid_returnOnlyValidPlaceholders() {
        TemplateParser parser = new TemplateParser();

        List<String> placeholders1 = parser.parseTemplate(INVALID_TEMPLATE_1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders1));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders1, List.of("value12")));

        List<String> placeholders2 = parser.parseTemplate(INVALID_TEMPLATE_2);
        Assert.assertTrue(CollectionUtils.isNotEmpty(placeholders2));
        Assert.assertTrue(CollectionUtils.containsAll(placeholders2, List.of("value22")));
    }

    @Test
    public void getPlaceholdersFromTemplate_withoutPlaceholders_returnEmpty() {
        TemplateParser parser = new TemplateParser();

        List<String> placeholders1 = parser.parseTemplate(WITHOUT_PLACEHOLDERS_TEMPLATE);
        Assert.assertTrue(CollectionUtils.isEmpty(placeholders1));
    }

}
