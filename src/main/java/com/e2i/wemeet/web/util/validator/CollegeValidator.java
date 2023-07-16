package com.e2i.wemeet.web.util.validator;

import com.e2i.wemeet.web.domain.member.Colleges;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollegeValidator implements ConstraintValidator<College, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String collegeName = value.trim();

        Colleges result = Arrays.stream(Colleges.values())
            .filter(colleges -> colleges.getCollegeName().equals(collegeName))
            .findFirst()
            .orElseGet(() -> {
                log.info("CollegeValidator isNotValid() - value: {}", collegeName);
                return null;
            });

        return result != null;
    }
}
