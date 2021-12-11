package com.example.facebookapi.business;

import lombok.Data;

@Data
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

