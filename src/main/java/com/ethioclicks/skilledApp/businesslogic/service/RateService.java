package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;

public interface RateService{
    void rateService(int rate, Services services, User user);

}
