package com.ibas.safetynet.data.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BrnInfoDto implements Serializable {
    private String ubrn;
    private LocalDate dob;
    private String personName;
    private String personNameEn;
    private String motherName;
    private String motherNameEn;
    private String motherNationality;
    private String motherNationalityEn;
    private String fatherName;
    private String fatherNameEn;
    private String fatherNationality;
    private String fatherNationalityEn;
    private String sex;
    private String placeOfBirth;
    private String placeOfBirthEn;
}
