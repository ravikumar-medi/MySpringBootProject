package com.in.dev.cbts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cbts_student_bus_mapping")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CbtsStudentBusMapping extends CbtsBaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private CbtsUser student;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private CbtsBus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private CbtsRoute route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private CbtsRouteStop stop;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;
}