package facebookapi.business.validators;

import facebookapi.business.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^((?!.*[\\s]).{5,16})");

        return pattern.matcher(password).matches();
    }
}
