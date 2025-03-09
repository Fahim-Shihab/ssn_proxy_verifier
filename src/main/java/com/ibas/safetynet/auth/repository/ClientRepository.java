package com.ibas.safetynet.auth.repository;

import com.ibas.safetynet.auth.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientInfo, Integer> {
    Optional<ClientInfo> findByUsername(String username);
}
