package com.in.dev.cbts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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