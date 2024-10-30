package com.fitness.service;


import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;

public interface UpdateService {

    public void populateMetricTypesWithUser(int userId, MetricType... types);
    public MetricEntry updateMetricEntry(MetricEntry entry);
    public DayLog updateDayLog(DayLog log);
}
