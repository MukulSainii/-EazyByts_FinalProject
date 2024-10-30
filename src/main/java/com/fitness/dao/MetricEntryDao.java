package com.fitness.dao;


import com.fitness.entity.MetricEntry;
import java.util.List;


public interface MetricEntryDao {

    public MetricEntry addMetricEntry(MetricEntry entry);

    public MetricEntry getMetricEntryById(int entryId);

    public List<MetricEntry> getAllMetricEntriesSorted();

    public MetricEntry editMetricEntry(MetricEntry updatedEntry);

    public void deleteMetricEntry(int entryId);
}

