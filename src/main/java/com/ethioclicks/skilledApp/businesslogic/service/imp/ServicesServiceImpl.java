
package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ServicesService;
import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ServicesServiceImpl implements ServicesService {

   private final ServicesRepo servicesRepo;
  private final   UserRegistrationService userRegistrationService;
  private final UserRepo userRepo;


    public ServicesServiceImpl(ServicesRepo servicesRepo, UserRegistrationService userRegistrationService, UserRepo userRepo) {
        this.servicesRepo = servicesRepo;
        this.userRegistrationService = userRegistrationService;
        this.userRepo = userRepo;
    }
    @Transactional
    @Override
    public ServicesModel saveService(ServicesModel servicesModel, String pid) {

        if (servicesModel == null) {
            throw new BadRequestException("Service model is required");
        }
        User user = userRepo.findByUserPublicId(pid).orElseThrow(()-> new BadRequestException("User not found"));

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
                existingService.setPostDate(LocalDateTime.now());
                return Mapper.toServiceModel(servicesRepo.save(existingService));
            }
            else {
                throw new BadRequestException("You dont have access to post on this Agency");
            }
        } else {

            Services services = Mapper.toServiceEntity(servicesModel);
            String servicePublicId =  UUID.randomUUID().toString();
            services.setServicePublicId(servicePublicId);
//            services.setUser(user);
            user.addServices(services);
//            services.setSkillCategory();
//            services.setUser(user);
            services.setPostDate(LocalDateTime.now());
            return Mapper.toServiceModel(servicesRepo.save(services));
        }
    }
    @Override
    public boolean isServiceOwner(String servicePublicId, String pid) {
        User user = userRegistrationService.getUser(pid);
        Optional<Services> services = servicesRepo.findByServicePublicId(servicePublicId);
        return user != null && services.isPresent() && user.getUserPublicId().equals(services.get().getUser().getUserPublicId());
    }

    @Transactional
    @Override
    public void deleteProduct(String servicePublicId) {
        Optional<Services> services = servicesRepo.findByServicePublicId(servicePublicId);
        if(services.isPresent()){
            servicesRepo.deleteServicesByServicePublicId(servicePublicId);
        }
    }
    @Override
    public ServicesModel getServiceByPublicId(String servicePublicId) {
        Optional<Services> servicesOptional = servicesRepo.findByServicePublicId(servicePublicId);
        if(!servicesOptional.isPresent()){
            return null;
        }
        return Mapper.toServiceModel(servicesOptional.get());
    }
    @Override
    public List<Services> getServicesByOwnerPublicId(String pid) {

        User user = userRegistrationService.getUser(pid);
        List<Services>ServiceByOwner = servicesRepo.findServicesByUser(user);

        return ServiceByOwner;
    }
    @Override
    public Page<Services> getAllServices(Pageable pageable) {
        Page<Services> services = servicesRepo.findAll(pageable);
        return services;
    }
}
