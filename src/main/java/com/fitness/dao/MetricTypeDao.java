package com.fitness.dao;


import com.fitness.entity.MetricType;
import java.util.List;


public interface MetricTypeDao {

    public MetricType addMetricType(MetricType type);

    public MetricType getMetricTypeById(int typeId);

    public List<MetricType> getAllMetricTypes();

    public MetricType editMetricType(MetricType updatedType);

    public void deleteMetricType(int typeId);
}

