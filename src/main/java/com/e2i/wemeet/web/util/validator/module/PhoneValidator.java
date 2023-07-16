package com.e2i.wemeet.web.util.validator.module;

import com.e2i.wemeet.web.util.validator.bean.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^01[016789]\\d{7,8}$");
    }
}
