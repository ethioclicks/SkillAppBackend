package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.AvailabilityHour;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AvailabilityHourRepo extends CrudRepository<AvailabilityHour, Integer> {
    @Modifying
    @Query(value = "DELETE FROM AVAILABILITY_HOUR WHERE AVAILABILITY_HOUR.ID=:availabilityHourId", nativeQuery = true)
    void deleteByIdManual(@Param("availabilityHourId") Integer availabilityHourId);

}
