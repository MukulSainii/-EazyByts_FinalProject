package com.fitness.service;


import com.fitness.entity.MetricEntry;
import com.fitness.entity.MetricType;
import com.fitness.entity.UserAccount;
import com.fitness.exception.*;
import java.util.Set;


public interface ValidateService {

    public void validateNewAccountSettings(Set<String> violations, UserAccount user, String passwordConfirmationEntry);
    public void validateNewAccountSettings(UserAccount user) throws InvalidUsernameException, InvalidPasswordException, InvalidEmailException;
    public void validateUsername(Set<String> violations, String username);
    public void validateUsername(String username) throws InvalidUsernameException;
    public void validatePassword(Set<String> violations, String password, String passwordConfirmationEntry);
    public void validatePassword(String password) throws InvalidPasswordException;
    public void validateEmail(Set<String> violations, String email);
    public void validateEmail(String email) throws InvalidEmailException;
    public void validateMetricTypes(int userId, MetricType... types) throws InvalidMetricTypeException;
    public void validateMetricEntry(MetricEntry entry) throws InvalidEntryException, InvalidMetricTypeException;

}

