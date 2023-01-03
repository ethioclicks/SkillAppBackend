package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProjectsRepo extends CrudRepository<Projects,Long> , JpaSpecificationExecutor<Projects> {

    @Modifying
    @Query(value = "DELETE FROM PROJECTS WHERE PROJECTS.ID=:id", nativeQuery = true)
    void deleteByIdManual(Long id);


//
//    ServicesRate findServiceRatingByServicesAndUser(Services services, User user);
//    @Query(value = "SELECT AVG(SERVICES_RATE.RATE) FROM SERVICES_RATE WHERE SERVICES_RATE.SERVICE_ID =:serviceId ", nativeQuery = true)
//    double getRating(Long serviceId);

}
