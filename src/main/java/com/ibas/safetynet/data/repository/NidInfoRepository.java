package com.ibas.safetynet.data.repository;

import com.ibas.safetynet.data.model.NidInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NidInfoRepository extends JpaRepository<NidInfo, Integer> {
    @Query(value = "select * from nid_info where (smart_id=:pNid or nid=:pNid) and dob=:pDob", nativeQuery = true)
    Optional<NidInfo> findByNidAndDob(@Param("pNid") String nid, @Param("pDob") LocalDate pDob);
}
