package facebookapi.business.annotations;

import facebookapi.business.validators.NotNullPostValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullPostValidator.class)
@Documented
public @interface NotNullPost {
    String message() default "Nie dodano ani zdjęcia, ani opisu.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
