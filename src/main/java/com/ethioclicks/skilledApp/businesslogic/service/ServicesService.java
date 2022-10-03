package com.ethioclicks.skilledApp.businesslogic.service;


import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;

public interface ServicesService {
    ServicesModel saveService (ServicesModel services, String pid);
    boolean isServiceOwner(String servicePublicId, String pid);
}
