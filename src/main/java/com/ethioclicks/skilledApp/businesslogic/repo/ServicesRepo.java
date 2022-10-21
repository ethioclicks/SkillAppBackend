package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicesRepo extends CrudRepository<Services,Long> , JpaSpecificationExecutor<Services>, PagingAndSortingRepository<Services, Long> {
    Optional<Services> findByServicePublicId(String servicePublicId);

    Services getServiceById(Long ServicesId);
    @Modifying
    @Query(value = "DELETE FROM SERVICES WHERE SERVICES.ID = :serviceId", nativeQuery = true)
    void deleteServicesByServicePublicId(@Param("serviceId") Long id);

    List<Services>findServicesByUser(User user);
}
