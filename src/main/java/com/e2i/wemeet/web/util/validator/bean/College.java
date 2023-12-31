package com.e2i.wemeet.web.util.validator.bean;

import com.e2i.wemeet.web.util.validator.module.CollegeValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollegeValidator.class)
public @interface College {

    String message() default "{College.RegisterBasicRequestDto.college}";

    Class[] groups() default {};

    Class[] payload() default {};
}
