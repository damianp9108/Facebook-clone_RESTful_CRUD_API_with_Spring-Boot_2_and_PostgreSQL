package com.example.facebookapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    private String fieldName;
    private String message;

    public ValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public ValidationError() {
    }


}

