package com.ibas.safetynet.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mfs_acc_owner_info")
public class MfsAccOwnerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 17, nullable = false)
    private String nid;

    @Column(name = "mfs_id", nullable = false)
    private Short mfsId;

    @Column(name = "mobile_number", length = 11, nullable = false)
    private String mobileNumber;

    @Column(name = "mobile_number_owned_by_nid")
    private boolean mobileNumberOwnedByNid;
}
