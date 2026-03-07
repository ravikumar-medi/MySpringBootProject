package com.in.dev.cbts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cbts_notifications")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsNotification extends CbtsBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CbtsUser user;

    private String message;

    @Column(name = "is_read")
    private Boolean isRead = false;
}