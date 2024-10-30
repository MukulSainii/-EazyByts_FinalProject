package com.fitness.serviceImpl;

import com.fitness.dao.DayLogDao;
import com.fitness.dao.MetricEntryDao;
import com.fitness.dao.UserAccountDao;
import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    UserAccountDao userDao;

    @Autowired
    MetricEntryDao entryDao;

    @Autowired
    DayLogDao logDao;

    public void populateMetricTypesWithUser(int userId, MetricType... types){
        for (MetricType type: types){
            type.setUser(userDao.getUserAccountById(userId));
        }
    }

    public MetricEntry updateMetricEntry(MetricEntry entry){
        return entryDao.editMetricEntry(entry);
    }

    public DayLog updateDayLog(DayLog log){
        return logDao.updateDayLog(log);
    }
}
