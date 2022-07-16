package com.hw.tdd.service;

import com.hw.tdd.exception.FileException;
import com.hw.tdd.exception.TemplateFormatException;

import java.util.List;
import java.util.Map;

public class FileProcessor {

    private final ScannerHelper scannerHelper;
    private final FileParser fileParser;
    private final TemplateParser templateParser;
    private final TemplateValidator templateValidator;

    public FileProcessor(ScannerHelper scannerHelper, FileParser fileParser, TemplateParser templateParser, TemplateValidator templateValidator) {
        this.scannerHelper = scannerHelper;
        this.fileParser = fileParser;
        this.templateParser = templateParser;
        this.templateValidator = templateValidator;
    }

    public FileProcessor(ScannerHelper scannerHelper) {
        this.scannerHelper = scannerHelper;
        this.fileParser = new FileParser();
        this.templateParser = new TemplateParser();
        this.templateValidator = new TemplateValidator();
    }

    public String processFile(String inputFilename, String outputFilename) throws FileException, TemplateFormatException {
        String template = fileParser.readTemplateFromFile(inputFilename);

        templateValidator.validate(template);

        List<String> placeholders = templateParser.parseTemplate(template);

        Map<String, String> placeholderValues = scannerHelper.askForPlaceholderValues(placeholders);

        String result = templateParser.replacePlaceholders(template, placeholderValues);
        fileParser.writeToFile(outputFilename, result);
        return result;
    }

}
