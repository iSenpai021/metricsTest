package com.clarity.software.technicalTest.api.service;

import com.clarity.software.technicalTest.api.model.Metric;
import com.clarity.software.technicalTest.api.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MetricService {

    private final MetricRepository metricRepository;

    @Autowired
    public MetricService(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    public Metric createMetric(Metric metric) {
        if(metric.getDate() == 0) {
            metric.setDate(Instant.now().getEpochSecond());
        }
        return metricRepository.save(metric);
    }

    public Metric updateMetric(int id, Metric metric) {
        metric.setId(id);
        return metricRepository.save(metric);
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetricsById(int id) {
        Optional<Metric> metric = metricRepository.findById(id);
        if(metric.isPresent()) {
            return metric.get();
        }
        throw new RuntimeException();
    }

    public List<Metric> getMetricsByDateRange(int from, int to) {
        return metricRepository.findByDateGreaterThanEqual(from);
    }

    public List<Metric> getMetricsBySystemAndNameAndDateRange(String metricSystem, String name, int from, int to) {
        return metricRepository.findByMetricSystemAndNameAndDateBetween(metricSystem, name, from, to);
    }
}
