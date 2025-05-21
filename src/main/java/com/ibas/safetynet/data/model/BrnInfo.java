package com.ibas.safetynet.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brn_details")
public class BrnInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 17, unique = true)
    private String ubrn;

    private LocalDate dob;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_name_en")
    private String personNameEn;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_name_en")
    private String motherNameEn;

    @Column(name = "mother_nationality", length = 100)
    private String motherNationality;

    @Column(name = "mother_nationality_en", length = 100)
    private String motherNationalityEn;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_name_en")
    private String fatherNameEn;

    @Column(name = "father_nationality", length = 100)
    private String fatherNationality;

    @Column(name = "father_nationality_en", length = 100)
    private String fatherNationalityEn;

    @Column(length = 4)
    private String sex;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "place_of_birth_en")
    private String placeOfBirthEn;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "sent_by")
    private Integer sentBy;
}
