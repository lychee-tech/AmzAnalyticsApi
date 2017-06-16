package com.leechi.amz.analytics.features.account.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = UniqueEmailExceptItselfValidator.class)
public @interface UniqueEmailExceptItself {
    String message() default "Email has been registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
