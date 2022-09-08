package com.ethioclicks.skilledApp.security.repo;

import com.ethioclicks.skilledApp.security.entity.Questions;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepo  extends CrudRepository<Questions, Long> {
}
