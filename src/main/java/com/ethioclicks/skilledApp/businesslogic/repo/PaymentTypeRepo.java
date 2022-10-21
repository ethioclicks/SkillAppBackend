package com.ethioclicks.skilledApp.businesslogic.repo;

import com.ethioclicks.skilledApp.businesslogic.entity.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepo  extends CrudRepository<PaymentType ,Long> {
}
