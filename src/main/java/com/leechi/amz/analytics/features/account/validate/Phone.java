package com.leechi.amz.analytics.features.account.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Your phone can only contains digital";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
