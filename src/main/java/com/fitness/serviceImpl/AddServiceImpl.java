package com.fitness.serviceImpl;

import com.fitness.dao.DayLogDao;
import com.fitness.dao.MetricEntryDao;
import com.fitness.dao.MetricTypeDao;
import com.fitness.dao.UserAccountDao;
import com.fitness.entity.DayLog;
import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.entity.UserAccount;
import com.fitness.service.AddService;
import com.fitness.service.LookupService;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    UserAccountDao userDao;

    @Autowired
    MetricTypeDao typeDao;

    @Autowired
    DayLogDao logDao;

    @Autowired
    MetricEntryDao entryDao;

    @Autowired
    LookupService lookupService;

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public UserAccount createNewAccount(UserAccount user){
        return userDao.addUserAccount(user);
    }

    public List<MetricType> addMetricTypes(MetricType... types){
        // list that will hold MetricTypes after having IDs assigned
        List<MetricType> populatedTypeList = new ArrayList();
        for (MetricType type : types){
            MetricType populatedType = typeDao.addMetricType(type);
            populatedTypeList.add(populatedType);
        }
        return populatedTypeList;
    }

    public MetricType addMetricType(MetricType type){
        return typeDao.addMetricType(type);
    }

    public DayLog addDayLog(DayLog log){
        return logDao.addDayLog(log);
    }

    public MetricEntry addMetricEntry(MetricEntry entry){
        return entryDao.addMetricEntry(entry);
    }

    public void fillDayLogGaps(int userId) {
        List<LocalDate> dates = lookupService.getDatesForUser(userId);
        int i;

        for (i = 0; i < dates.size()-1; i++){
            LocalDate firstDate = dates.get(i);
            LocalDate secondDate = dates.get(i + 1);
            // if the number of days between the first date and the second date is greater than 0:
            long missingDays = DAYS.between(firstDate, secondDate) - 1;
            if (missingDays > 0){
                DayLog fillerLog = new DayLog();
                fillerLog.setUser(userDao.getUserAccountById(userId));
                for (long j = 1; j <= missingDays; j++){ // plusDays() takes a long, didn't want to upcast the counter each loop
                    // get the date that's missing
                    LocalDate fillerDate = firstDate.plusDays(j);
                    fillerLog.setLogDate(fillerDate);
                    addDayLog(fillerLog);
                }
            }
        }
    }

    @Override
    public UserAccount populateNewUserFromForm(HttpServletRequest request) {
        UserAccount user = new UserAccount();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setTimeZone(request.getParameter("timeZone"));
        return user;
    }
}
