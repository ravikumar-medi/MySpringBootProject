package com.myproject.cbts.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.cbts.models.CbtsUser;

import java.util.Optional;

public interface CbtsUserRepository extends JpaRepository<CbtsUser, Long> {
    Optional<CbtsUser> findByUsername(String username);
}