package com.example.WebAstro.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyErrorMessage {

    static class Names {
        public static final String CODE = "code";
        public static final String MESSAGE = "message";
    }

    private final Integer code;
    private final String message;

    @JsonCreator
    public MyErrorMessage(@JsonProperty(Names.CODE) Integer code, @JsonProperty(Names.MESSAGE) String message) {
        this.code = code;
        this.message = message;
    }

    @JsonProperty(Names.CODE)
    public Integer getCode() {
        return code;
    }

    @JsonProperty(Names.MESSAGE)
    public String getMessage() {
        return message;
    }


}

