package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cbts_route_stops")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsRouteStop extends CbtsBaseEntity {

    @Column(name = "stop_name")
    private String stopName;

    private Double latitude;
    private Double longitude;

    @Column(name = "stop_order")
    private Integer stopOrder;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private CbtsRoute route;
}