package com.e2i.wemeet.web.util.validator.bean;

import com.e2i.wemeet.web.util.validator.module.EmailDomainValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDomainValidator.class)
public @interface EmailDomain {
    String message() default "{Email.Format}";

    Class[] groups() default {};

    Class[] payload() default {};
}
