package com.fitness.serviceImpl;

import com.fitness.dao.DayLogDao;
import com.fitness.dao.MetricEntryDao;
import com.fitness.dao.MetricTypeDao;
import com.fitness.entity.MetricEntry;
import com.fitness.service.DeleteService;
import com.fitness.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImpl implements DeleteService {

    @Autowired
    MetricEntryDao entryDao;

    @Autowired
    DayLogDao logDao;

    @Autowired
    MetricTypeDao typeDao;

    @Autowired
    LookupService lookupService;

    public void deleteMetricEntry(int entryId) {
        entryDao.deleteMetricEntry(entryId);
    }

    public void deleteDayLog(int dayLogId){
        logDao.deleteDayLog(dayLogId);
    }

    public void deleteMetricType(int typeId){
        for (MetricEntry entry : lookupService.getMetricEntriesForType(typeId)){
            entryDao.deleteMetricEntry(entry.getMetricEntryId());
        }
        typeDao.deleteMetricType(typeId);
    }
}
