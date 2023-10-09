package com.example.WebAstro;

import org.apache.commons.lang3.StringUtils;

public class Validator {
    public static void validate(String name) {
        if (!StringUtils.isNotBlank(name)) {
            throw new ValidationException("Incorrect name");
        }
    }
}
