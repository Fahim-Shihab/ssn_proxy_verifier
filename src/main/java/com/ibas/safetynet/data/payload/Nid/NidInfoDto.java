package com.ibas.safetynet.data.payload.Nid;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NidInfoDto {
    @Size(min = 10, max = 17)
    private String nationalId;
    @Size(min = 10, max = 17)
    private String pin;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String nameEn;
    @Size(max = 6)
    private String gender;
    @Size(max = 4)
    private String bloodGroup;
    @Size(max = 255)
    private String father;
    @Size(max = 255)
    private String mother;
    private String photo;
    @Size(max = 16)
    private String religion;
    @Size(max = 17)
    private String nidFather;
    @Size(max = 17)
    private String nidMother;
    private AddressDto permanentAddress;
    private AddressDto presentAddress;
}
