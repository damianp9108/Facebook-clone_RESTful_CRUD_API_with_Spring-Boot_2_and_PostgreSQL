package facebookapi.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationFieldError {

    private String fieldName;
    private String message;

}

