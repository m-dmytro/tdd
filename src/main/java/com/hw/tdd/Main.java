package com.hw.tdd;

import com.hw.tdd.service.ConsoleProcessor;
import com.hw.tdd.service.FileProcessor;
import com.hw.tdd.service.ScannerHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScannerHelper scannerHelper = new ScannerHelper(scanner);

        String result;
        if (scannerHelper.askIsConsoleFlow()) {
            result = consoleFlow(scannerHelper);
        } else {
            result = fileFlow(scannerHelper);
        }

        if (StringUtils.isNotBlank(result)) {
            System.out.println("Result: ");
            System.out.println(result);
        }
        scanner.close();
    }

    private static String consoleFlow(ScannerHelper scannerHelper) {
        String result = null;
        try {
            ConsoleProcessor processor = new ConsoleProcessor(scannerHelper);
            result = processor.processTemplate();
        } catch (Exception ex) {
            System.exit(-1);
        }
        return result;
    }

    private static String fileFlow(ScannerHelper scannerHelper) {
        String result = null;
        try {
            FileProcessor processor = new FileProcessor(scannerHelper);
            String inputFilename = scannerHelper.askForFileName("input");
            String outputFilename = scannerHelper.askForFileName("output");
            result = processor.processFile(inputFilename, outputFilename);
        } catch (Exception ex) {
            System.exit(-1);
        }
        return result;
    }

}
