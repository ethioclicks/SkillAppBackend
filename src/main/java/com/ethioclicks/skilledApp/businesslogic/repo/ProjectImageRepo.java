package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.ProjectImages;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProjectImageRepo extends CrudRepository<ProjectImages,Long> {
    @Modifying
    @Query(value = "DELETE FROM PROJECT_IMAGES WHERE PROJECT_IMAGES.ID=:id", nativeQuery = true)
    void deleteByIdManual(Long id);
}
