package com.ibas.safetynet.data.model;

import com.ibas.safetynet.data.payload.nid.AddressDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nid_details")
public class NidInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "smart_id", length = 10, unique = true)
    private String smartId;

    @Column(length = 17, unique = true)
    private String nid;

    private LocalDate dob;

    @Column(name = "name_bn")
    private String nameBn;

    @Column(name = "name_en")
    private String nameEn;

    @Column(length = 16)
    private String gender;

    @Column(name = "blood_group", length = 3)
    private String bloodGroup;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_nid", length = 17)
    private String fatherNid;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_nid", length = 17)
    private String motherNid;

    @Column(columnDefinition = "text")
    private String photo;

    @Column(length = 32)
    private String religion;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "permanent_address", columnDefinition = "jsonb")
    private AddressDto permanentAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "present_address", columnDefinition = "jsonb")
    private AddressDto presentAddress;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "sent_by")
    private Integer sentBy;
}
