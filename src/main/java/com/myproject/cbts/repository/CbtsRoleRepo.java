package com.myproject.cbts.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.cbts.models.CbtsRole;

import java.util.Optional;

public interface CbtsRoleRepo extends JpaRepository<CbtsRole, Long> {

    Optional<CbtsRole> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);
}