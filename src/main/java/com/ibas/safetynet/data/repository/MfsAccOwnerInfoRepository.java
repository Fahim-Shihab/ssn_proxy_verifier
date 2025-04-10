package com.ibas.safetynet.data.repository;

import com.ibas.safetynet.data.model.MfsAccOwnerInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MfsAccOwnerInfoRepository extends JpaRepository<MfsAccOwnerInfo, Integer> {
    @Query(value = "select *from mfs_acc_owner_info where mfs_id = :pMfsId and mobile_number = :pMobileNumber", nativeQuery = true)
    Optional<MfsAccOwnerInfo> findByMfsIdAndMobileNumber(@Param("pMfsId") Short pMfsId, @Param("pMobileNumber") String pMobileNumber);
}
