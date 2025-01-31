package com.fitness.serviceImpl;

import com.fitness.dao.DayLogDao;
import com.fitness.dao.MetricEntryDao;
import com.fitness.dao.MetricTypeDao;
import com.fitness.dao.UserAccountDao;
import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.entity.UserAccount;
import com.fitness.service.LookupService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookupServiceImpl implements LookupService {

    @Autowired
    DayLogDao logDao;

    @Autowired
    MetricEntryDao entryDao;

    @Autowired
    MetricTypeDao typeDao;

    @Autowired
    UserAccountDao userDao;


    // DayLog implements Comparator, should be sorted in ascending order by logDate
    public List<DayLog> getDayLogsForUser(int userId){
        List<DayLog> dayLogs = logDao.getAllDayLogs().stream()
                .filter(log -> log.getUser().getUserAccountId() == userId)
                .sorted()
                .collect(Collectors.toList());
        return dayLogs;
    }

    // sorted (natural order)
    public List<LocalDate> getDatesForUser(int userId){
        return getDayLogsForUser(userId).stream()
                .map(DayLog::getLogDate)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<MetricEntry> getMetricEntriesForType(int typeId){
        return entryDao.getAllMetricEntriesSorted().stream()
                .filter(entry -> entry.getMetricType().getMetricTypeId() == typeId)
                .collect(Collectors.toList());
    }


    public List<MetricEntry> getMetricEntriesForUser(int userId){
        return entryDao.getAllMetricEntriesSorted().stream()
                .filter(entry -> entry.getDayLog().getUser().getUserAccountId() == userId)
                .collect(Collectors.toList());
    }

    public List<MetricEntry> getMetricEntriesByDate(int userId, LocalDate date){

        List<MetricEntry> entryList = getMetricEntriesForUser(userId).stream()
                .filter(entry -> entry.getDayLog().getLogDate().isEqual(date))
                .collect(Collectors.toList());
        return entryList;
    }

    public List<MetricType> getMetricTypesForUser(int userId){
        return typeDao.getAllMetricTypes().stream()
                .filter(type -> type.getUser().getUserAccountId() == userId)
                .collect(Collectors.toList());
    }

    public MetricEntry getMetricEntryById(int entryId) {
        return entryDao.getMetricEntryById(entryId);
    }

    public MetricType getMetricTypeById(int typeId){
        return typeDao.getMetricTypeById(typeId);
    }

    public UserAccount getUserAccountById(int userId){
        return userDao.getUserAccountById(userId);
    }

    public DayLog getDayLogByDateAndUser(int userId, LocalDate convertedDate) {
        return getDayLogsForUser(userId).stream()
                .filter(log -> log.getLogDate().isEqual(convertedDate))
                .findFirst().orElse(null);
    }

    public UserAccount getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    @Override
    public String getNotesForUserAndDate(int userId, LocalDate convertedDate) {
        return getDayLogByDateAndUser(userId, convertedDate).getNotes();
    }
}

