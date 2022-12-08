package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.ServicesRate;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRateRepo extends CrudRepository<ServicesRate,Long> , JpaSpecificationExecutor<ServicesRate> {


    ServicesRate findServiceRatingByServicesAndUser(Services services, User user);
    @Query(value = "SELECT AVG(SERVICES_RATE.RATE) FROM SERVICES_RATE WHERE SERVICES_RATE.SERVICE_ID =:serviceId ", nativeQuery = true)
    double getRating(Long serviceId);
}
