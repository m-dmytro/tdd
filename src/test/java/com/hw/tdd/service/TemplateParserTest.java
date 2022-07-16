package com.hw.tdd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateParserTest {

    private static final String VALID_TEMPLATE_1 = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";
    private static final String VALID_TEMPLATE_2 = "Some text input 1: #{value21}, Some text input 2: #{value22}.";
    private static final String VALID_TEMPLATE_3 = "Some text input 1: #{value31}, Some text input 2: #{value32}, Some text input 3: #{value32}.";
    private static final String INVALID_TEMPLATE_1 = "Some text input 1: #{{value11}, Some text input 2: #{value12}.";
    private static final String INVALID_TEMPLATE_2 = "Some text input 1: {value21}, Some text input 2: #{value22}.";
    private static final String WITHOUT_PLACEHOLDERS_TEMPLATE = "Some text input 1: {value1}, Some text input 2: value2.";

    @Test
    public void getPlaceholdersFromTemplate_allValidPlaceholder_returnAllPlaceholders() {
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
    public void getPlaceholdersFromTemplate_partInvalidPlaceholder_returnOnlyValidPlaceholders() {
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

        List<String> placeholders = parser.parseTemplate(WITHOUT_PLACEHOLDERS_TEMPLATE);
        Assert.assertTrue(CollectionUtils.isEmpty(placeholders));
    }

    @Test
    public void replaceTemplatePlaceholders_allValidPlaceholders_validOutput() {
        Map<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("value1", "processed_value1");
        placeholderValues.put("value2", "processed_value2");
        placeholderValues.put("value3", "processed_value3");
        placeholderValues.put("value21", "processed_value21");
        placeholderValues.put("value22", "processed_value22");
        placeholderValues.put("value31", "processed_value31");
        placeholderValues.put("value32", "processed_value32");

        TemplateParser parser = new TemplateParser();

        String result1 = parser.replacePlaceholders(VALID_TEMPLATE_1, placeholderValues);
        Assert.assertNotNull(result1);
        Assert.assertEquals("Some text input 1: #{processed_value1}, Some text input 2: #{processed_value2}, Some text input 3: #{processed_value3}.", result1);

        String result2 = parser.replacePlaceholders(VALID_TEMPLATE_2, placeholderValues);
        Assert.assertNotNull(result2);
        Assert.assertEquals("Some text input 1: #{processed_value21}, Some text input 2: #{processed_value22}.", result2);

        String result3 = parser.replacePlaceholders(VALID_TEMPLATE_3, placeholderValues);
        Assert.assertNotNull(result3);
        Assert.assertEquals("Some text input 1: #{processed_value31}, Some text input 2: #{processed_value32}, Some text input 3: #{processed_value32}.", result3);
    }

    @Test
    public void replaceTemplatePlaceholders_allValidPlaceholders_notReturnInputPlaceholder() {
        Map<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("value1", "processed_value1");
        placeholderValues.put("value2", "processed_value2");
        placeholderValues.put("value3", "processed_value3");
        placeholderValues.put("value21", "processed_value21");
        placeholderValues.put("value22", "processed_value22");
        placeholderValues.put("value31", "processed_value31");
        placeholderValues.put("value32", "processed_value32");

        TemplateParser parser = new TemplateParser();

        String result1 = parser.replacePlaceholders(VALID_TEMPLATE_1, placeholderValues);
        Assert.assertNotNull(result1);
        Assert.assertNotEquals("Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.", result1);

        String result2 = parser.replacePlaceholders(VALID_TEMPLATE_2, placeholderValues);
        Assert.assertNotNull(result2);
        Assert.assertNotEquals("Some text input 1: #{value21}, Some text input 2: #{value22}.", result2);

        String result3 = parser.replacePlaceholders(VALID_TEMPLATE_3, placeholderValues);
        Assert.assertNotNull(result3);
        Assert.assertNotEquals("Some text input 1: #{value31}, Some text input 2: #{value32}, Some text input 3: #{value32}.", result3);
    }

    @Test
    public void replaceTemplatePlaceholders_partInvalidPlaceholders_validOutput() {
        Map<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("value11", "processed_value11");
        placeholderValues.put("value12", "processed_value12");
        placeholderValues.put("value21", "processed_value21");
        placeholderValues.put("value22", "processed_value22");

        TemplateParser parser = new TemplateParser();

        String result1 = parser.replacePlaceholders(INVALID_TEMPLATE_1, placeholderValues);
        Assert.assertNotNull(result1);
        Assert.assertEquals("Some text input 1: #{{value11}, Some text input 2: #{processed_value12}.", result1);

        String result2 = parser.replacePlaceholders(INVALID_TEMPLATE_2, placeholderValues);
        Assert.assertNotNull(result2);
        Assert.assertEquals("Some text input 1: {value21}, Some text input 2: #{processed_value22}.", result2);
    }

    @Test
    public void replaceTemplatePlaceholders_withoutPlaceholders_outputAsInput() {
        Map<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("value1", "processed_value1");
        placeholderValues.put("value2", "processed_value2");
        placeholderValues.put("value3", "processed_value3");
        placeholderValues.put("value21", "processed_value21");
        placeholderValues.put("value22", "processed_value22");
        placeholderValues.put("value31", "processed_value31");
        placeholderValues.put("value32", "processed_value32");

        TemplateParser parser = new TemplateParser();

        String result = parser.replacePlaceholders(WITHOUT_PLACEHOLDERS_TEMPLATE, placeholderValues);
        Assert.assertNotNull(result);
        Assert.assertEquals(WITHOUT_PLACEHOLDERS_TEMPLATE, result);
    }

}
