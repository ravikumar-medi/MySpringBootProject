package com.in.dev.cbts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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