package com.e2i.wemeet.web.util.validator;

import com.e2i.wemeet.web.domain.member.AdmissionYears;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdmissionYearValidator implements ConstraintValidator<AdmissionYear, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String admissionYear = value.trim();
        if (admissionYear.length() != 2) {
            return false;
        }

        AdmissionYears admissionYears = Arrays.stream(AdmissionYears.values())
            .filter(year -> year.getYear().equals(admissionYear))
            .findFirst()
            .orElseGet(() -> {
                log.info("AdmissionYearValidator isNotValid() - value: {}", admissionYear);
                return null;
            });

        return admissionYears != null;
    }
}
