package com.ibas.safetynet.data.repository;

import com.ibas.safetynet.data.model.BrnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BrnInfoRepository extends JpaRepository<BrnInfo, Integer> {
    Optional<BrnInfo> findBrnInfoByUbrnAndDob(String ubrn, LocalDate dob);
}
