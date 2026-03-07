package com.in.dev.cbts.repository;


import com.in.dev.cbts.model.CbtsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CbtsUserRepository extends JpaRepository<CbtsUser, Long> {
    Optional<CbtsUser> findByUsername(String username);
}