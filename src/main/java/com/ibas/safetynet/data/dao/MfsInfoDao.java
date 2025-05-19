package com.ibas.safetynet.data.dao;

import com.ibas.safetynet.data.payload.BrnInfoDto;
import com.ibas.safetynet.data.payload.MfsAccOwnerInfoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MfsInfoDao {
    @PersistenceContext
    private EntityManager em;

    public void saveMfsAccOwnerInfo(MfsAccOwnerInfoDto dto) {

    }
}
