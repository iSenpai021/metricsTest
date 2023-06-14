package com.clarity.software.technicalTest.api.controller;

import com.clarity.software.technicalTest.api.model.Metric;
import com.clarity.software.technicalTest.api.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public ResponseEntity<Metric> createMetric(@RequestBody Metric metric) {
        try {
            Metric createdMetric = metricService.createMetric(metric);
            createdMetric.setDate(Instant.now().getEpochSecond());
            return new ResponseEntity<>(createdMetric, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Metric>> getAllMetrics() {
        try {
            List<Metric> metrics = metricService.getAllMetrics();
            return new ResponseEntity<>(metrics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metric> getMetricById(@PathVariable int id) {
        Metric metric = metricService.getMetricsById(id);
        if (metric == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(metric, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Metric> updateMetric(@PathVariable int id, @RequestBody Metric updatedMetric) {
        Metric existingMetric = metricService.getMetricsById(id);

        if (existingMetric.getId() != id) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if system and name match the existing metric
        if (!existingMetric.getMetricSystem().equals(updatedMetric.getMetricSystem()) ||
                !existingMetric.getName().equals(updatedMetric.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Update the metric values
        if (updatedMetric.getValue() < 0) {
            existingMetric.setValue(updatedMetric.getValue());
        } else {
            existingMetric.setValue(existingMetric.getValue() + 1);
        }

        Metric updatedMetricResult = metricService.updateMetric(existingMetric.getId(), existingMetric);
        return new ResponseEntity<>(updatedMetricResult, HttpStatus.OK);
    }
}
