package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.model.AgencyModel;
import org.springframework.stereotype.Service;

public interface AgencyService {
    AgencyModel saveAgency(AgencyModel agencyModel,String pid );
}
