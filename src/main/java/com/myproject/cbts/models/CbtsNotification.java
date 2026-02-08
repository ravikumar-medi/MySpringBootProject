package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

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