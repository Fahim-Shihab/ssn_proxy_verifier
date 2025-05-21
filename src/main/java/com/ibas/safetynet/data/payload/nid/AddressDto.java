package com.ibas.safetynet.data.payload.nid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto implements Serializable {
    private String division;
    private String district;
    private String rmo;
    private String upozila;
    private String cityCorporationOrMunicipality;
    private String unionOrWard;
    private String postOffice;
    private String postalCode;
    private String wardForUnionPorishod;
    private String additionalMouzaOrMoholla;
    private String additionalVillageOrRoad;
    private String homeOrHoldingNo;
    private String region;
}
