package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cbts_roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsRole extends CbtsBaseEntity {

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName; // ADMIN, DRIVER, STUDENT
}


