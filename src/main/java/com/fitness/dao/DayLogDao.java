package com.fitness.dao;


import com.fitness.entity.DayLog;
import java.util.List;


public interface DayLogDao {

    public DayLog addDayLog(DayLog dayLog);

    public DayLog getDayLogById(int dayLogId);

    public List<DayLog> getAllDayLogs();

    public DayLog updateDayLog(DayLog updatedDayLog);

    public void deleteDayLog(int dayLogId);
}

