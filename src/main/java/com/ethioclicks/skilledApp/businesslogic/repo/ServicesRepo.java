package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;
import java.util.Optional;

public interface ServicesRepo extends CrudRepository<Services,Long> , JpaSpecificationExecutor<Services>, PagingAndSortingRepository<Services, Long> {
    Optional<Services> findByServicePublicId(String servicePublicId);

    Services getServiceById(Long ServicesId);
    @Modifying
    @Query(value = "DELETE FROM SERVICES WHERE SERVICES.ID = :serviceId", nativeQuery = true)
    void deleteServicesByServicePublicId(@Param("serviceId") Long id);
    List<Services>findServicesByUser(User user);
    @Query(value= " SELECT SERVICES.* FROM SERVICES" +
            " INNER JOIN SKILL_CATEGORY" +
            " ON SERVICES.SKILL_CATEGORY_ID = SKILL_CATEGORY.ID" +
            " WHERE  SKILL_CATEGORY.CATEGORY_NAME LIKE %:keyword% or SERVICES.SKILLS LIKE %:keyword% or" +
            " SERVICES.TAG like %:keyword% or " +
            " SERVICES.LOCATION_COVERAGE like %:keyword% " , nativeQuery = true)
    List<Services> getServiceByKeyword( @Param("keyword") String keyword);
    @Query(value= " SELECT SERVICES.* FROM SERVICES" +
            " INNER JOIN SKILL_CATEGORY" +
            " ON SERVICES.SKILL_CATEGORY_ID = SKILL_CATEGORY.ID" +
            " WHERE  (SKILL_CATEGORY.CATEGORY_NAME LIKE %:keyword% or SERVICES.SKILLS LIKE %:keyword% or" +
            " SERVICES.TAG like %:keyword% or " +
            " SERVICES.LOCATION_COVERAGE like %:keyword% )" +
            " AND SERVICES.RATE  BETWEEN :rate and 5" , nativeQuery = true)
    List<Services> getServiceByKeyword( @Param("keyword") String keyword, @Param("rate") Integer rate);
    List<Services>getServicesByLocationCoverage(String locationCoverage, Pageable pageable);
}

