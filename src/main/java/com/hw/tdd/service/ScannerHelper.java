package com.hw.tdd.service;

import com.hw.tdd.exception.TemplateFormatException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ScannerHelper {

    private final Scanner scanner;

    public ScannerHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean askIsConsoleFlow() {
        System.out.print("Do you want to work with console? N/Y - ");
        String isReadFromConsole = scanner.nextLine();

        if (isReadFromConsole.toUpperCase().equals("Y")) {
           return true;
        } else {
            return false;
        }
    }

    public String askForFileName(String fileDefinition) {
        System.out.print("Enter "+fileDefinition+" filename: ");
        return scanner.nextLine();
    }

    public String askForTemplate() {
        System.out.println("Enter your template: ");
        return scanner.nextLine();
    }

    public Map<String, String> askForPlaceholderValues(List<String> placeholders) throws TemplateFormatException {
        Map<String, String> placeholderValues = new HashMap<>();
        for (String placeholder: placeholders) {
            System.out.println("Enter '"+placeholder+"': ");
            String value = scanner.nextLine();

            if (StringUtils.isBlank(value)) {
                System.err.println("Placeholder should be empty");
                throw new TemplateFormatException("Placeholder should be empty");
            }

            placeholderValues.put(placeholder, value);
        }
        return placeholderValues;
    }

}
