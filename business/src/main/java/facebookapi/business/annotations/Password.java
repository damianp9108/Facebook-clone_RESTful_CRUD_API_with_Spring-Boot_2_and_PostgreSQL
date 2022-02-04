package facebookapi.business.annotations;

import facebookapi.business.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

    String message() default "Haslo musi zawierac 5-16 znakow, spacja niedozwolona";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
