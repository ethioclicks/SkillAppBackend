package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.Reviews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ReviewsRepo extends CrudRepository<Reviews, Long> {
    @Modifying
    @Query(value = "DELETE FROM REVIEWS WHERE REVIEWS.ID=:id", nativeQuery = true)
    void deleteByIdManual(Long id);

//    List<ServicesFeedback> findAllByServicesAndIsReply(Services services, boolean isReplay);
}
