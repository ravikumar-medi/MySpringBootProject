package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cbts_bus")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsBus extends CbtsBaseEntity {

    @Column(name = "bus_number", nullable = false, unique = true)
    private String busNumber;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private CbtsUser driver;

    private String status; // ACTIVE, INACTIVE
}