package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "cbts_bus_location",
    indexes = {
        @Index(name = "idx_bus_time", columnList = "bus_id, location_time")
    }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsBusLocation extends CbtsBaseEntity {

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private CbtsBus bus;

    private Double latitude;
    private Double longitude;
    private Double speed;
    private String direction;

    @Column(name = "location_time")
    private LocalDateTime locationTime;
}