
package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.AvailabilityHour;
import com.ethioclicks.skilledApp.businesslogic.entity.PaymentType;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.entity.SkillCategory;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
import com.ethioclicks.skilledApp.businesslogic.repo.AvailabilityHourRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.PaymentTypeRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.SkillCategoryRepo;
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
  private final PaymentTypeRepo paymentTypeRepo;

  private final SkillCategoryRepo skillCategoryRepo;
  private final AvailabilityHourRepo  availabilityHourRepo;


    public ServicesServiceImpl(ServicesRepo servicesRepo, UserRegistrationService userRegistrationService, UserRepo userRepo, PaymentTypeRepo paymentTypeRepo, SkillCategoryRepo skillCategoryRepo, AvailabilityHourRepo availabilityHourRepo) {
        this.servicesRepo = servicesRepo;
        this.userRegistrationService = userRegistrationService;
        this.userRepo = userRepo;
        this.paymentTypeRepo = paymentTypeRepo;
        this.skillCategoryRepo = skillCategoryRepo;
        this.availabilityHourRepo = availabilityHourRepo;
    }
    @Transactional
    @Override
    public ServicesModel saveService(ServicesModel servicesModel, String pid) {

        if (servicesModel == null) {
            throw new BadRequestException("Service model is required");
        }
        User user = userRepo.findByUserPublicId(pid).orElseThrow(()-> new BadRequestException("User not found"));

        SkillCategory skillCategory = skillCategoryRepo.findById(servicesModel.getSkillCategory()).orElseThrow(()-> new BadRequestException("Category not found"));
        PaymentType paymentType = paymentTypeRepo.findById(servicesModel.getPaymentType()).orElseThrow(()-> new BadRequestException("Payment type is not found"));
        if (servicesModel.getId() != null && !servicesModel.getId().toString().isEmpty()) {
            Services existingService = servicesRepo.findById(servicesModel.getId()).orElseThrow(() -> new BadRequestException("Service id not found"));

            if (isServiceOwner(existingService.getServicePublicId(), pid)) {
                existingService.setDescription(servicesModel.getDescription());
                existingService.setSkillCategory(skillCategory);
                existingService.setYearInService(servicesModel.getYearInService());
                existingService.setServiceRegisteredDate(servicesModel.getServiceRegisteredDate());
                existingService.setSkills(servicesModel.getSkills());
                existingService.setImageUrl(servicesModel.getImageUrl());
                existingService.setLocationCoverage(servicesModel.getLocationCoverage());
                existingService.setNumberOfServicesCompleted(servicesModel.getNumberOfServicesCompleted());
                existingService.setPaymentPrice(servicesModel.getPaymentPrice());
                existingService.setPaymentRemark(servicesModel.getPaymentRemark());
                existingService.setAvailabilityHours(servicesModel.getAvailabilityHours());
                existingService.setPaymentType(paymentType);
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
            user.addServices(services);
            services.setSkillCategory(skillCategory);
            services.setPaymentType(paymentType);
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
    public void deleteService(Long serviceId,String pid) {
//        User user = userRepo.findByUserPublicId(pid).orElseThrow(()-> new BadRequestException("User not found"));

        Optional<Services> services = servicesRepo.findById(serviceId);
        if(services.isPresent()){

            if(isServiceOwner(services.get().getServicePublicId(),pid)){
                for(AvailabilityHour availabilityHour: services.get().getAvailabilityHours()){
                    availabilityHourRepo.deleteByIdManual(availabilityHour.getId());
                }
                servicesRepo.deleteServicesByServicePublicId(serviceId);
            }else{
                throw new BadRequestException("You don't have permission to delete");
            }
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
