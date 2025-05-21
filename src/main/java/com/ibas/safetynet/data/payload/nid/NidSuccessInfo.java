package com.ibas.safetynet.data.payload.nid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NidSuccessInfo implements Serializable {
    private NidInfoDto data;
}
