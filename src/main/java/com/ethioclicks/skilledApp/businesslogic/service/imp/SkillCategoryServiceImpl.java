package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.SkillCategory;
import com.ethioclicks.skilledApp.businesslogic.repo.SkillCategoryRepo;
import com.ethioclicks.skilledApp.businesslogic.service.SkillCategoryService;
import org.springframework.stereotype.Service;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepo  skillCategoryRepo;

    public SkillCategoryServiceImpl(SkillCategoryRepo skillCategoryRepo) {
        this.skillCategoryRepo = skillCategoryRepo;
    }
    @Override
    public SkillCategory saveCategory(SkillCategory skillCategory) {
        SkillCategory newCategory = new SkillCategory();
        newCategory.setCategoryName(skillCategory.getCategoryName());
        newCategory.setSubCategory(skillCategory.getSubCategory());
        newCategory.setDescription(skillCategory.getDescription());
        return skillCategoryRepo.save(newCategory);
    }
}
