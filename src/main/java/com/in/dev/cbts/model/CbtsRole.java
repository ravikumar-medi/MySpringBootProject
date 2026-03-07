package com.in.dev.cbts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cbts_roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsRole extends CbtsBaseEntity {

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName; // ADMIN, DRIVER, STUDENT
}


