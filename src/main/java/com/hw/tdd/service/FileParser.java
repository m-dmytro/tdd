package com.hw.tdd.service;

import com.hw.tdd.exception.FileException;

import java.io.*;

public class FileParser {

    public String readTemplateFromFile(File file) throws FileException {
        StringBuilder template = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                template.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error during parsing file " + file.getName());
            throw new FileException("Error during parsing file " + file.getName());
        }
        return template.toString();
    }

    public String readTemplateFromFile(String filename) throws FileException {
        StringBuilder template = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                template.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error during parsing file " + filename);
            throw new FileException("Error during parsing file " + filename);
        }
        return template.toString();
    }

    public void writeToFile(String filename, String text) throws FileException {
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            byte[] strToBytes = text.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            System.err.println("Error during write to file " + filename);
            throw new FileException("Error during write to file " + filename);
        }
    }

}
