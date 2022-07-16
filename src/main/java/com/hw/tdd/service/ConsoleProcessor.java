package com.hw.tdd.service;

import com.hw.tdd.exception.TemplateFormatException;

import java.util.List;
import java.util.Map;

public class ConsoleProcessor {

    private final ScannerHelper scannerHelper;
    private final TemplateParser templateParser;
    private final TemplateValidator templateValidator;

    public ConsoleProcessor(ScannerHelper scannerHelper, TemplateParser templateParser, TemplateValidator templateValidator) {
        this.scannerHelper = scannerHelper;
        this.templateParser = templateParser;
        this.templateValidator = templateValidator;
    }

    public ConsoleProcessor(ScannerHelper scannerHelper) {
        this.scannerHelper = scannerHelper;
        this.templateParser = new TemplateParser();
        this.templateValidator = new TemplateValidator();
    }

    public String processTemplate() throws TemplateFormatException {
        String template = askForTemplate();

        templateValidator.validate(template);

        List<String> placeholders = templateParser.parseTemplate(template);

        Map<String, String> placeholderValues = askForPlaceholderValues(placeholders);

        return templateParser.replacePlaceholders(template, placeholderValues);
    }

    public String askForTemplate() {
        return scannerHelper.askForTemplate();
    }

    public Map<String, String> askForPlaceholderValues(List<String> placeholders) throws TemplateFormatException {
        return scannerHelper.askForPlaceholderValues(placeholders);
    }

}
