package facebookapi.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private List<ValidationError> errors = new ArrayList<>();

    public ValidationErrorResponse addError(ValidationError error) {
        this.errors.add(error);

        return this;
    }
}
