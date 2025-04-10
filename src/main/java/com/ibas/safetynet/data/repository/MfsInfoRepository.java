package com.ibas.safetynet.data.repository;

import com.ibas.safetynet.data.model.MfsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MfsInfoRepository extends JpaRepository<MfsInfo, Long> {
}
