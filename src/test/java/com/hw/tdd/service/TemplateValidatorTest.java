package com.hw.tdd.service;

import com.hw.tdd.exception.TemplateFormatException;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class TemplateValidatorTest {

    private static final String VALID_TEMPLATE = "Some text input 1: #{value1}, Some text input 2: #{value2}, Some text input 3: #{value3}.";

    @Test
    public void validateTemplate_valid() throws TemplateFormatException {
        TemplateValidator validator = new TemplateValidator();
        validator.validate(VALID_TEMPLATE);
    }

    @Test
    public void validateTemplate_nullTemplate() throws TemplateFormatException {
        Assertions.assertThrows(TemplateFormatException.class, () -> {
            TemplateValidator validator = new TemplateValidator();
            validator.validate(null);
        });
    }

    @Test //junit 5
    public void validateTemplate_emptyTemplate() throws TemplateFormatException {
        Assertions.assertThrows(TemplateFormatException.class, () -> {
            TemplateValidator validator = new TemplateValidator();
            validator.validate("");
        });
    }

    @Rule //junit 4
    public ExpectedException thrown = ExpectedException.none();

    @org.junit.Test
    public void validateTemplate_nullTemplate_testUsingRuleAnnotation() throws TemplateFormatException {
        thrown.expect(TemplateFormatException.class);
        thrown.expectMessage("Empty template");

        TemplateValidator validator = new TemplateValidator();
        validator.validate(null);
    }

}
