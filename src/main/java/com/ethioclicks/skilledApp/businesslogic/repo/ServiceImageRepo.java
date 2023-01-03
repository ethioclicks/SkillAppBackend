package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.ServiceImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ServiceImageRepo extends CrudRepository<ServiceImage, Long> {

    @Modifying
    @Query(value = "DELETE FROM SERVICE_IMAGE WHERE SERVICE_IMAGE.ID=:id", nativeQuery = true)
    void deleteByIdManual(Long id);
}
