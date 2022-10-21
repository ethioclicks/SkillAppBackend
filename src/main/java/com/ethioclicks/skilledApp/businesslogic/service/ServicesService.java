package com.ethioclicks.skilledApp.businesslogic.service;



import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ServicesService {
    ServicesModel saveService(ServicesModel services, String pid);
    boolean isServiceOwner(String servicePublicId, String pid);
    void deleteService(Long serviceId, String pid);
    ServicesModel getServiceByPublicId(String servicePublicId);

   List<Services> getServicesByOwnerPublicId(String pid);

    Page<Services> getAllServices(Pageable pageable);
}



