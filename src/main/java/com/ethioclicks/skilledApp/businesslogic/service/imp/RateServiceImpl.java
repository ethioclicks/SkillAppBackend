package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.entity.ServicesRate;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRateRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.RateService;
import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class RateServiceImpl implements RateService {

    private final ServicesRateRepo servicesRateRepo;
    private final ServicesRepo servicesRepo;

    public RateServiceImpl(ServicesRateRepo servicesRateRepo, ServicesRepo servicesRepo) {
        this.servicesRateRepo = servicesRateRepo;
        this.servicesRepo = servicesRepo;
    }
    @Override
    public void rateService(int rate, Services services, User user) {
        Services servicesToRate = servicesRepo.getServiceById(services.getId());
        DecimalFormat df = new DecimalFormat("#.#");
        ServicesRate serviceServicesRateNew = new ServicesRate();
        ServicesRate serviceRating = servicesRateRepo.findServiceRatingByServicesAndUser(services, user);
        if(serviceRating ==null){
            serviceServicesRateNew.setServices(servicesToRate);
            serviceServicesRateNew.setRate(rate);
            serviceServicesRateNew.setUser(user);
            servicesRateRepo.save(serviceServicesRateNew);
        }else{
            serviceRating.setRate(rate);
            servicesRateRepo.save(serviceRating);
        }
        double servicesRate = servicesRateRepo.getRating(services.getId());
        servicesToRate.setRate(Double.parseDouble(df.format(servicesRate)));
        servicesRepo.save(servicesToRate);


    }
}

