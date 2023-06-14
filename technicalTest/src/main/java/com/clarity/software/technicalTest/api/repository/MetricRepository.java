package com.clarity.software.technicalTest.api.repository;

import com.clarity.software.technicalTest.api.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric,Integer> {

    List<Metric> findByDateGreaterThanEqual(int from);

    Metric findMetricsById(int id);

    List<Metric> findByMetricSystemAndNameAndDateBetween(String metricSystem, String name, int from, int to);

}
