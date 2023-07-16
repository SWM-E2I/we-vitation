package com.e2i.wemeet.web.util.validator.bean;

import com.e2i.wemeet.web.util.validator.module.AdmissionYearValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdmissionYearValidator.class)
public @interface AdmissionYear {

    String message() default "{AdmissionYear.RegisterBasicRequestDto.admissionYear}";

    Class[] groups() default {};

    Class[] payload() default {};

}
