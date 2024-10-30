package com.fitness.service;


import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.entity.UserAccount;

import java.time.LocalDate;
import java.util.List;

public interface LookupService {

    public List<DayLog> getDayLogsForUser(int userId);
    public List<LocalDate> getDatesForUser(int userId);
    public List<MetricEntry> getMetricEntriesForType(int typeId);
    public List<MetricEntry> getMetricEntriesForUser(int userId);
    public List<MetricEntry> getMetricEntriesByDate(int userId, LocalDate date);
    public List<MetricType> getMetricTypesForUser(int userId);
    public MetricEntry getMetricEntryById(int entryId);
    public MetricType getMetricTypeById(int typeId);
    public UserAccount getUserAccountById(int userId);
    public DayLog getDayLogByDateAndUser(int userId, LocalDate convertedDate);
    public UserAccount getUserByUsername(String username);
    public String getNotesForUserAndDate(int userId, LocalDate convertedDate);
}

