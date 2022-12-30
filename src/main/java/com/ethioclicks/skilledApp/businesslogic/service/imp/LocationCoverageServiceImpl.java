package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.LocationCoverage;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.repo.LocationCoverageRepo;
import com.ethioclicks.skilledApp.businesslogic.service.LocationCoverageService;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationCoverageServiceImpl implements LocationCoverageService {
    private final LocationCoverageRepo locationCoverageRepo;
    private final UserRepo userRepo;

    public LocationCoverageServiceImpl(LocationCoverageRepo locationCoverageRepo, UserRepo userRepo) {
        this.locationCoverageRepo = locationCoverageRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LocationCoverage saveLocationCoverage(LocationCoverage locationCoverage) {
        if (locationCoverage == null) {
            throw new BadRequestException("Location Coverage Name is empty");
        }
        LocationCoverage newLocation = new LocationCoverage();
        newLocation.setName(locationCoverage.getName());

        return locationCoverageRepo.save(newLocation);
    }
    @Override
    public LocationCoverage getLocationById(Long locationId) {
        Optional<LocationCoverage> locationCoverage = locationCoverageRepo.findById(locationId);
        if (!locationCoverage.isPresent()) {
            return null;
        }
        return locationCoverage.get();
    }
    @Override
    public LocationCoverage updateLocationCoverageService(LocationCoverage locationCoverage) {
        if (locationCoverage == null) {
            throw new BadRequestException("Location Coverage name is empty");
        }
        if (locationCoverage.getId() != null) {
            Optional<LocationCoverage> existingLocationCoverage = locationCoverageRepo.findById(locationCoverage.getId());
            existingLocationCoverage.get().setName(locationCoverage.getName());
            return locationCoverageRepo.save(existingLocationCoverage.get());
        }
        throw new BadRequestException("location coverage id is not found");
    }
    @Override
    public void deleteLocationCoverage(Long locationId) {
        locationCoverageRepo.deleteById(locationId);
    }
    private String[] getSearchWords(String keyword) {
        String[] searchWords = keyword.split(" ");
        if (searchWords != null && searchWords.length > 0) {
            for (int i = 0; i < searchWords.length; i++) {
                searchWords[i] = searchWords[i].trim();
            }
        }
        return searchWords;
    }
    @Override
    public List<LocationCoverage> autoCompleteLocationCoverageList(String keyword) {
        List<LocationCoverage> matchingProducts = new ArrayList<>();
        String[] searchWords = getSearchWords(keyword);

        if (searchWords != null) {
            if (searchWords.length > 1) {
                List partialMatchProducts = new ArrayList();
                for (String searchWord : searchWords) {
                    List<LocationCoverage> partialResult = locationCoverageRepo.getLocationCoverageByKeyword(searchWord);
                    if (partialResult != null && !partialResult.isEmpty()) {
                        partialMatchProducts.addAll(partialResult);
                    }
                }
                if (!partialMatchProducts.isEmpty()) {
                    matchingProducts.addAll(partialMatchProducts);
                }
            }
            if (searchWords.length == 1) {
                matchingProducts = locationCoverageRepo.getLocationCoverageByKeyword(keyword);
            }
        }
        return matchingProducts;
    }
}
