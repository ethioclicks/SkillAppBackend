package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface SkillCategoryRepo extends CrudRepository<SkillCategory, Long>, JpaSpecificationExecutor<SkillCategory> {
}
