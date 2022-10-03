package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RateService extends CrudRepository<Rate,Long> {
}
