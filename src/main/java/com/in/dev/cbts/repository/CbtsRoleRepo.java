package com.in.dev.cbts.repository;


import com.in.dev.cbts.model.CbtsRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CbtsRoleRepo extends JpaRepository<CbtsRole, Long> {

    Optional<CbtsRole> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);
}