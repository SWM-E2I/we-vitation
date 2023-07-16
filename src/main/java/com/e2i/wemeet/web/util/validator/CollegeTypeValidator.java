package com.e2i.wemeet.web.util.validator;

import com.e2i.wemeet.web.domain.member.CollegeTypes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollegeTypeValidator implements ConstraintValidator<CollegeType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String collegeTypeName = value.trim();

        CollegeTypes result = Arrays.stream(CollegeTypes.values())
            .filter(colleges -> colleges.getCollegeTypeName().equals(collegeTypeName))
            .findFirst()
            .orElseGet(() -> {
                log.info("CollegeTypeValidator isNotValid() - value: {}", collegeTypeName);
                return null;
            });

        return result != null;
    }
}
