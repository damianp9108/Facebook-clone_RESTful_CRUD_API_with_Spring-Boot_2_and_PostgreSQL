package facebookapi.business.annotations;

import facebookapi.business.validators.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
@Documented
public @interface UserName {

    String message() default "Nazwa uzytkownia musi zawierac 4-14 znakow, spacja niedozwolona";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
