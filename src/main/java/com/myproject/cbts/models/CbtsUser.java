package com.myproject.cbts.models;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

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