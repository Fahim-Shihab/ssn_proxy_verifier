package com.ibas.safetynet.data.dao;

import com.ibas.safetynet.data.payload.BrnInfoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BrnInfoDao {
    @PersistenceContext
    private EntityManager em;

    public void saveBrnInfo(BrnInfoDto dto) {

    }
}
