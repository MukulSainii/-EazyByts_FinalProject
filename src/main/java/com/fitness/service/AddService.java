package com.fitness.service;



import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.entity.UserAccount;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AddService {

    public UserAccount createNewAccount(UserAccount user);
    public List<MetricType> addMetricTypes(MetricType... types);
    public MetricType addMetricType(MetricType type);
    public DayLog addDayLog(DayLog log);
    public MetricEntry addMetricEntry(MetricEntry entry);
    public void fillDayLogGaps(int userId);
    public UserAccount populateNewUserFromForm(HttpServletRequest request);
}
