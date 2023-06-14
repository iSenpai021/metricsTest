package com.clarity.software.technicalTest.api.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "metricSystem")
    private String metricSystem;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private long date;

    @Column(name = "value")
    private int value;


}