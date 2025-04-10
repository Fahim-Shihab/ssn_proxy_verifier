package com.ibas.safetynet.data.payload.Nid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NidInfoDto {
    private String nationalId;
    private String pin;
    private String name;
    private String nameEn;
    private String gender;
    private String bloodGroup;
    private String father;
    private String mother;
    private String photo;
    private String religion;
    private String nidFather;
    private String nidMother;
    private AddressDto permanentAddress;
    private AddressDto presentAddress;
}
