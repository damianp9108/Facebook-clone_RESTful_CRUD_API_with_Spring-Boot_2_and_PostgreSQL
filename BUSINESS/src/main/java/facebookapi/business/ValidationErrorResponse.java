package facebookapi.business;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    private List<ValidationError> errors = new ArrayList<>();

    // setters, getters

    public ValidationErrorResponse addError(ValidationError error) {
        this.errors.add(error);
        return this;
    }
}
