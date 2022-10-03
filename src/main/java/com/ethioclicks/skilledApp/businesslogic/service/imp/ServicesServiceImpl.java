
package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ServicesService;
import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class ServicesServiceImpl implements ServicesService {

   private final ServicesRepo servicesRepo;
  private final   UserRegistrationService userRegistrationService;


    public ServicesServiceImpl(ServicesRepo servicesRepo, UserRegistrationService userRegistrationService) {
        this.servicesRepo = servicesRepo;
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public ServicesModel saveService(ServicesModel servicesModel, String pid) {

        if (servicesModel == null) {
            throw new BadRequestException("Service model is required");
        }
        User user = userRegistrationService.getUser(pid);
        if (user == null) {
            throw new BadRequestException("User not found");
        }
        if (servicesModel.getId() != null && !servicesModel.getId().toString().isEmpty()) {
            Services existingService = servicesRepo.findById(servicesModel.getId()).orElseThrow(() -> new BadRequestException("Service id not found"));

            if (isServiceOwner(existingService.getServicePublicId(), pid)) {
                existingService.setDescription(servicesModel.getDescription());
                existingService.setSkillCategory(servicesModel.getSkillCategory());
                existingService.setYearInService(servicesModel.getYearInService());
                existingService.setServiceRegisteredDate(servicesModel.getServiceRegisteredDate());
                existingService.setSkills(servicesModel.getSkills());
                existingService.setImageUrl(servicesModel.getImageUrl());
                existingService.setLocationCoverage(servicesModel.getLocationCoverage());
                existingService.setNumberOfServicesCompleted(servicesModel.getNumberOfServicesCompleted());
                existingService.setPaymentPrice(servicesModel.getPaymentPrice());
                existingService.setPaymentRemark(servicesModel.getPaymentRemark());
                existingService.setAvailabilityHours(servicesModel.getAvailabilityHours());
                existingService.setPaymentType(servicesModel.getPaymentType());
                existingService.setPostDate(new Date());
                return Mapper.toServiceModel(servicesRepo.save(existingService));
            }
            else {
                throw new BadRequestException("You dont have access to post on this Agency");
            }
        } else {

            Services services = Mapper.toServiceEntity(servicesModel);
            services.setServicePublicId(UUID.randomUUID().toString());
            services.setUser(user);

            services.setPostDate(new Date());
            return Mapper.toServiceModel(servicesRepo.save(services));
        }

    }
    @Override
    public boolean isServiceOwner(String servicePublicId, String pid) {
        User user = userRegistrationService.getUser(pid);
        Optional<Services> services = servicesRepo.findByServicePublicId(servicePublicId);
        return user != null && services.isPresent() && user.getUserPublicId().equals(services.get().getUser().getUserPublicId());
    }
}
