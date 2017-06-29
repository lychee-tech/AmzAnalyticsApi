package com.leechi.amz.analytics.features.account.validate;

import com.sun.deploy.security.ValidationState;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email has already Registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload()default {};
}
