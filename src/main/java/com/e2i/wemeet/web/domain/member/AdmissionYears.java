package com.e2i.wemeet.web.domain.member;

import lombok.Getter;

@Getter
public enum AdmissionYears {
    YEAR_2023("23"),
    YEAR_2022("22"),
    YEAR_2021("21"),
    YEAR_2020("20"),
    YEAR_2019("19"),
    YEAR_2018("18"),
    YEAR_2017("17"),
    YEAR_2016("16"),
    YEAR_2015("15")
    ;

    private final String year;

    AdmissionYears(String year) {
        this.year = year;
    }
}
