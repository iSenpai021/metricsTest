package com.clarity.software.technicalTest.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricSummary {

    @Column(nullable = false)
    private String metricSystem;

    @Column(nullable = false)
    private String name;

    @Column(name = "from_time", nullable = false)
    private Long from;

    @Column(name = "to_time", nullable = false)
    private Long to;

    private Integer value;
}
