package com.ibas.safetynet.data.payload.nid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NidInfoSaveDto implements Serializable {
    @NotNull
    @Size(min = 10, max = 17)
    private String nationalId;
    @NotNull
    @Size(min = 10, max = 17)
    private String pin;
    @NotNull
    private LocalDate dob;
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
    private Integer sentBy;
    private LocalDateTime receivedAt;
}
