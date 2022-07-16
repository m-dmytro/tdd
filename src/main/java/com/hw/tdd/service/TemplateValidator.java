package com.hw.tdd.service;

import com.hw.tdd.exception.TemplateFormatException;
import org.apache.commons.lang3.StringUtils;

public class TemplateValidator {

    public void validate(String template) throws TemplateFormatException {
        if (StringUtils.isBlank(template)){
            System.err.println("Empty template");
            throw new TemplateFormatException("Empty template");
        }
    }

}
