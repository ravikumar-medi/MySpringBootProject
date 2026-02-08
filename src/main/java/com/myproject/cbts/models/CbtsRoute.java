package com.myproject.cbts.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cbts_route")


public class CbtsRoute extends CbtsBaseEntity {

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "distance_km")
    private BigDecimal distanceKm;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<CbtsRouteStop> stops;
}