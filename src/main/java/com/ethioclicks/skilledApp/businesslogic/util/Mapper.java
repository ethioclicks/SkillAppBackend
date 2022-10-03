package com.ethioclicks.skilledApp.businesslogic.util;


import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.model.AgencyModel;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;

public class Mapper {
    public static ServicesModel toServiceModel(Services services) {
        if(services!=null){
            ServicesModel servicesModel = new ServicesModel();
            servicesModel.setId(services.getId());
            servicesModel.setServicePublicId(services.getServicePublicId());
            servicesModel.setSkillCategory(services.getSkillCategory());
            servicesModel.setDescription(services.getDescription());
            servicesModel.setYearInService(services.getYearInService());
            servicesModel.setSkills(services.getSkills());
            servicesModel.setImageUrl(services.getImageUrl());
            servicesModel.setUser(services.getUser());
            servicesModel.setLocationCoverage(services.getLocationCoverage());
            servicesModel.setAvailabilityTypeId(services.getAvailabilityTypeId());
            servicesModel.setNumberOfServicesCompleted(services.getNumberOfServicesCompleted());
            servicesModel.setAvailabilityHours(services.getAvailabilityHours());
            servicesModel.setRate(services.getRate());
            servicesModel.setPaymentType(services.getPaymentType());
            servicesModel.setPaymentRemark(services.getPaymentRemark());
            servicesModel.setPostDate(services.getPostDate());
        }
        return null;
    }

    public static Services toServiceEntity(ServicesModel servicesModel) {
        if(servicesModel!=null){
            Services services = new Services();
            services.setId(servicesModel.getId());
            services.setServicePublicId(servicesModel.getServicePublicId());
            services.setSkillCategory(servicesModel.getSkillCategory());
            services.setDescription(servicesModel.getDescription());
            services.setYearInService(servicesModel.getYearInService());
            services.setSkills(servicesModel.getSkills());
            services.setImageUrl(servicesModel.getImageUrl());
            services.setUser(servicesModel.getUser());
            services.setLocationCoverage(servicesModel.getLocationCoverage());
            services.setAvailabilityTypeId(servicesModel.getAvailabilityTypeId());
            services.setNumberOfServicesCompleted(servicesModel.getNumberOfServicesCompleted());
            services.setAvailabilityHours(servicesModel.getAvailabilityHours());
            services.setRate(servicesModel.getRate());
            services.setPaymentType(servicesModel.getPaymentType());
            services.setPaymentRemark(servicesModel.getPaymentRemark());
            services.setPostDate(servicesModel.getPostDate());
        }

        return null;
    }
    public static AgencyModel toAgencyModel(Agency agency) {
        if(agency!=null){
            AgencyModel agencyModel = new AgencyModel();
            agencyModel.setId(agency.getId());
            agencyModel.setDescription(agency.getDescription());
            agencyModel.setAgencyPublicId(agency.getAgencyPublicId());
            agencyModel.setCity(agency.getCity());
            agencyModel.setStreet(agency.getStreet());
            agencyModel.setPhone(agency.getPhone());
            agencyModel.setEmail(agency.getEmail());
            agencyModel.setOwner(agency.getOwner());
            agencyModel.setPostDate(agency.getPostDate());
        }

        return null;
    }

    public static Agency toAgencyEntity(AgencyModel agencyModel) {
        if(agencyModel!=null){
            Agency agency = new Agency();
            agency.setId(agencyModel.getId());
            agency.setDescription(agencyModel.getDescription());
            agency.setAgencyPublicId(agencyModel.getAgencyPublicId());
            agency.setCity(agencyModel.getCity());
            agency.setStreet(agencyModel.getStreet());
            agency.setPhone(agencyModel.getPhone());
            agency.setEmail(agencyModel.getEmail());
            agency.setOwner(agencyModel.getOwner());
            agency.setPostDate(agencyModel.getPostDate());
        }

        return null;
    }
}
