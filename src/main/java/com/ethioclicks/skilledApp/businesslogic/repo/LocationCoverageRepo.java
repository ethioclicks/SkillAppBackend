package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.LocationCoverage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationCoverageRepo extends CrudRepository<LocationCoverage, Long> {

    @Query(value = "SELECT * FROM LOCATION_COVERAGE WHERE LOCATION_COVERAGE.NAME like %:keyword%", nativeQuery = true)
    List<LocationCoverage> getLocationCoverageByKeyword(String keyword);
    List<LocationCoverage> findByNameContains(String keyword);
}
