package facebookapi.business.validators;

import facebookapi.business.annotations.UserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^((?!.*[\\s]).{4,14})");

        return pattern.matcher(userName).matches();
    }
}
