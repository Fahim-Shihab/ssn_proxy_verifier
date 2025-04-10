package com.ibas.safetynet.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mfs_info")
public class MfsInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Short id;
    @Column(name = "name_bn")
    private String nameBn;
    @Column(name = "name_en")
    private String nameEn;
}
