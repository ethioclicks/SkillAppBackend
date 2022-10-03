package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.SkillCategory;
import org.springframework.data.repository.CrudRepository;

public interface SkillCategoryRepo extends CrudRepository<SkillCategory, Long> {
}
