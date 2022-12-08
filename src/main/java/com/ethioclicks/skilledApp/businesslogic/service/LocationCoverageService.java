package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.LocationCoverage;

import java.util.List;

public interface LocationCoverageService {
    LocationCoverage saveLocationCoverage(LocationCoverage locationCoverage);
    LocationCoverage getLocationById(Long locationId);
    LocationCoverage updateLocationCoverageService(LocationCoverage locationCoverage);
    void deleteLocationCoverage(Long locationId);
    List<LocationCoverage> autoCompleteLocationCoverageList(String keyword);
}
