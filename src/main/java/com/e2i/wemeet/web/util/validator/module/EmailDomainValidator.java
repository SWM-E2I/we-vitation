package com.e2i.wemeet.web.util.validator.module;

import com.e2i.wemeet.web.util.validator.bean.EmailDomain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String regex = "\\w+\\.ac\\.kr$";
        value.matches(regex);
        return true;
    }
}
