package facebookapi.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationErrorResponse {
    private List<ValidationFieldError> fieldErrors = new ArrayList<>();
    private ArrayList<ValidationObjectError> objectErrors = new ArrayList<>();


    public ValidationErrorResponse addFieldError(ValidationFieldError error) {
        this.fieldErrors.add(error);

        return this;
    }

    public ValidationErrorResponse addObjectError(ValidationObjectError error){
        this.objectErrors.add(error);

        return this;
    }

}
