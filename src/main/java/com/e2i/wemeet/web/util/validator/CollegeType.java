package com.e2i.wemeet.web.util.validator;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollegeTypeValidator.class)
public @interface CollegeType {

    String message() default "{CollegeType.RegisterBasicRequestDto.collegeType}";

    Class[] groups() default {};

    Class[] payload() default {};

}
