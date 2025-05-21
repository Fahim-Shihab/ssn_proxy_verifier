package com.ibas.safetynet.data.payload.mfs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MfsAccOwnerInfoSaveDto implements Serializable {
    @NotNull
    @Size(min = 10, max = 17)
    private String nid;
    @NotNull
    private Short mfsId;
    @NotNull
    @Size(min = 11, max = 11)
    private String mobileNumber;
    @NotNull
    private Boolean mobileNumberOwnedByNid;
    private Integer sentBy;
    private LocalDateTime receivedAt;
}
