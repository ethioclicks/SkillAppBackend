package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.entity.ServicesFeedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesFeedBackRepo extends CrudRepository<ServicesFeedback, Long> {
    List<ServicesFeedback> findAllByServicesAndIsReply(Services services, boolean isReplay);
}
