package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesRepo extends CrudRepository<Services,Long> , JpaSpecificationExecutor<Services>, PagingAndSortingRepository<Services, Long> {
    Optional<Services> findByServicePublicId(String servicePublicId);

    Services getServiceById(Long ServicesId);

    void deleteServicesByServicePublicId(String servicePublicId);

    List<Services>findServicesByUser(User user);
}
