package com.hw.tdd.service;

import com.hw.tdd.extensions.TemporaryDirectoryExtension;
import com.hw.tdd.exception.FileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileParserTest {

    @Test
    public void readTemplateFromFile_success_validPathAndFileNotEmpty() throws FileException {
        FileParser parser = new FileParser();

        String template1 = parser.readTemplateFromFile("TemplateInput1.txt");
        Assertions.assertNotNull(template1);
        Assertions.assertEquals("Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.", template1);

        String template2 = parser.readTemplateFromFile("TemplateInput2.txt");
        Assertions.assertNotNull(template2);
        Assertions.assertEquals("Some text input 1: #{value21}, Some text input 2: #{value22}.", template2);
    }

    @Test
    public void readTemplateFromFile_fail_invalidPath() throws FileException {
        Assertions.assertThrows(FileException.class, () -> {
            FileParser parser = new FileParser();
            parser.readTemplateFromFile("randomPath.txt");
        });
    }

    @Test
    public void writeToFile_success_validPath() throws FileException {
        FileParser parser = new FileParser();

        String text = "Some text input 1: #{pr_value1}, Some text input 2: #{pr_value2}, Some text input 3: #{pr_value3}.";
        parser.writeToFile("TemplateOutput.txt", text);

        String template = parser.readTemplateFromFile("TemplateOutput.txt");
        Assertions.assertEquals(text, template);
    }

    @Test
    @ExtendWith(TemporaryDirectoryExtension.class)
    public void readTemplateFromFile_usingTempDir(Path tempDir) throws FileException, IOException {
        String text = "Some text input 1: #{pr_value1}, Some text input 2: #{pr_value2}, Some text input 3: #{pr_value3}.";
        Path testFile = tempDir.resolve("tempTest.txt");
        Files.write(testFile, text.getBytes());

        FileParser parser = new FileParser();

        String template = parser.readTemplateFromFile(testFile.toFile());
        Assertions.assertNotNull(template);
        Assertions.assertEquals(text, template);
    }

}
