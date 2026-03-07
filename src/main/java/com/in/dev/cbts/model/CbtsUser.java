package com.in.dev.cbts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "cbts_users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CbtsUser extends CbtsBaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    private String status; // ACTIVE, INACTIVE

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cbts_user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<CbtsRole> roles;
}