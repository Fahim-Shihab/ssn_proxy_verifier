package com.ibas.safetynet.data.dao;

import com.ibas.safetynet.data.payload.Nid.NidInfoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class NidInfoDao {
    @PersistenceContext
    private EntityManager em;

    public void saveNidInfo(NidInfoDto dto) {

    }
}
