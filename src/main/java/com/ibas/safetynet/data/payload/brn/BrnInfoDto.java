package com.ibas.safetynet.data.payload.brn;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BrnInfoDto implements Serializable {
    @Size(min = 17, max = 17)
    private String ubrn;
    @NotNull
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
