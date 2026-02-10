package com.myproject.cbts.repository;

import com.myproject.cbts.models.CbtsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CbtsUserRepo extends JpaRepository<CbtsUser, Long> {

    Optional<CbtsUser> findByUsername(String username);

    boolean existsByUsername(String username);
}