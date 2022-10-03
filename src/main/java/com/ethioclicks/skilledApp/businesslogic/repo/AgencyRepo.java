package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AgencyRepo extends CrudRepository<Agency, Long> {
    Optional<Agency> findByAgencyPublicId(String agencyPublicId);
}
