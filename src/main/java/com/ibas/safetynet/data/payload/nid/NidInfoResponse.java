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
public class NidInfoResponse implements Serializable {
    private String status;
    private String statusCode;
    private NidSuccessInfo success ;
    private NidFailureInfo error;
}
