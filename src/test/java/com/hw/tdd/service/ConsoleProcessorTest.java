package com.hw.tdd.service;

import com.hw.tdd.exception.TemplateFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ConsoleProcessorTest {

    private ScannerHelper scannerHelper;
    private TemplateParser templateParser;
    private TemplateValidator templateValidator;

    private ConsoleProcessor sut;

    @BeforeEach
    void turnUp() {
        this.scannerHelper = mock(ScannerHelper.class);
        this.templateParser = mock(TemplateParser.class);
        this.templateValidator = mock(TemplateValidator.class);

        sut = new ConsoleProcessor(scannerHelper, templateParser, templateValidator);
    }

    @Test
    void processTemplateFromConsole() throws TemplateFormatException {
        String template = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";
        String expectedResult = "Some text input 1: #{processed_value1}, Some text input 2: #{processed_value2}, Some text input 3: #{processed_value3}.";

        List<String> templatePlaceholders = Arrays.asList("value1", "value2", "value3");

        Map<String, String> placeholderValues = new HashMap<>();
        placeholderValues.put("value1", "processed_value1");
        placeholderValues.put("value2", "processed_value2");
        placeholderValues.put("value3", "processed_value3");
        placeholderValues.put("value21", "processed_value21");
        placeholderValues.put("value22", "processed_value22");
        placeholderValues.put("value31", "processed_value31");
        placeholderValues.put("value32", "processed_value32");

        when(scannerHelper.askForTemplate()).thenReturn(template);
        doNothing().when(templateValidator).validate(template);
        when(templateParser.parseTemplate(template)).thenReturn(templatePlaceholders);
        when(scannerHelper.askForPlaceholderValues(templatePlaceholders)).thenReturn(placeholderValues);
        when(templateParser.replacePlaceholders(template, placeholderValues)).thenReturn(expectedResult);

        String actualResult = sut.processTemplate();

        Assertions.assertEquals(expectedResult, actualResult);
    }

}
