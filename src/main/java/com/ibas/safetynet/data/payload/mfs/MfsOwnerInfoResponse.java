package com.ibas.safetynet.data.payload.mfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MfsOwnerInfoResponse implements Serializable {
    private Boolean AccountExists;
    private Boolean NIDMatched;
    private Boolean NIDMobileMatched;
    private String Message;
}
