package com.ethioclicks.skilledApp.businesslogic.util;


import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
//import com.ethioclicks.skilledApp.businesslogic.entity.ServicesFeedback;
import com.ethioclicks.skilledApp.businesslogic.enums.FEEDBACK_TYPE;
import com.ethioclicks.skilledApp.businesslogic.model.AgencyModel;
//import com.ethioclicks.skilledApp.businesslogic.model.ServicesFeedbackModel;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Mapper {
    public static ServicesModel toServiceModel(Services services) {
        if(services!=null){
            ServicesModel servicesModel = new ServicesModel();
            servicesModel.setId(services.getId());
            servicesModel.setTag(services.getTag());
            servicesModel.setServicePublicId(services.getServicePublicId());
//            servicesModel.setSkillCategory(services.getSkillCategory().getId());
            servicesModel.setDescription(services.getDescription());
            servicesModel.setYearInService(services.getYearInService());
            servicesModel.setSkills(services.getSkills());
            servicesModel.setImageUrl(services.getImageUrl());
            servicesModel.setUser(services.getUser());
            servicesModel.setLocationCoverage(services.getLocationCoverage());
            servicesModel.setAvailabilityTypeId(services.getAvailabilityTypeId());
            servicesModel.setNumberOfServicesCompleted(services.getNumberOfServicesCompleted());
            servicesModel.setAvailabilityHours(services.getAvailabilityHours());
            //servicesModel.setRate(services.getRate());
            servicesModel.setReviews(services.getReviews());
            servicesModel.setProjects(services.getProjects());
            servicesModel.setServiceImages(services.getServiceImages());
            servicesModel.setPaymentPrice(services.getPaymentPrice());
            servicesModel.setPaymentRemark(services.getPaymentRemark());
            servicesModel.setPostDate(services.getPostDate());

            return  servicesModel;
        }
        return null;
    }

    public static Services toServiceEntity(ServicesModel servicesModel) {
        if(servicesModel!=null){
            Services services = new Services();
            services.setId(servicesModel.getId());
            services.setTag(servicesModel.getTag());
            services.setServicePublicId(servicesModel.getServicePublicId());
//            services.setSkillCategory(servicesModel.getSkillCategory());
            services.setDescription(servicesModel.getDescription());
            services.setYearInService(servicesModel.getYearInService());
            services.setSkills(servicesModel.getSkills());
            services.setImageUrl(servicesModel.getImageUrl());
            services.setUser(servicesModel.getUser());
            services.setLocationCoverage(servicesModel.getLocationCoverage());
            services.setAvailabilityTypeId(servicesModel.getAvailabilityTypeId());
            services.setNumberOfServicesCompleted(servicesModel.getNumberOfServicesCompleted());
            services.setAvailabilityHours(servicesModel.getAvailabilityHours());
            //services.setRate(servicesModel.getRate());
            services.setProjects(servicesModel.getProjects());
            services.setReviews(servicesModel.getReviews());
            services.setServiceImages(servicesModel.getServiceImages());
            services.setPaymentPrice(servicesModel.getPaymentPrice());
            services.setPaymentRemark(servicesModel.getPaymentRemark());
            services.setPostDate(servicesModel.getPostDate());

            return services;
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
            return  agencyModel;
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
            return agency;
        }

        return null;
    }
    public static Projects toProjectEntity(ProjectsModel projectsModel){
        if (projectsModel!=null){
            Projects projects = new Projects();
            projects.setId(projectsModel.getId());
            projects.setProjectsGivenTo(projectsModel.getProjectsGivenTo());
            projects.setTotalCost(projectsModel.getTotalCost());
            projects.setTitle(projectsModel.getTitle());
            projects.setProjectImages(projectsModel.getProjectImages());
            projects.setDescription(projectsModel.getDescription());
            projects.setTotalCost(projectsModel.getTotalCost());



        }
        return null;
    }
    public static ProjectsModel toProjectModel(Projects projects){

        if (projects!=null){
            ProjectsModel projectsModel = new ProjectsModel();
            projectsModel.setId(projects.getId());
            projectsModel.setProjectsGivenTo(projects.getProjectsGivenTo());
            projectsModel.setTotalCost(projects.getTotalCost());
            projectsModel.setTitle(projects.getTitle());
            projectsModel.setDescription(projects.getDescription());
            projectsModel.setTotalCost(projects.getTotalCost());
            projectsModel.setProjectImages(projects.getProjectImages());
        }
        return null;
    }
//    public static ServicesFeedback toServiceFeedback(ServicesFeedbackModel servicesFeedbackModel) {
//        if(servicesFeedbackModel!=null){
//            ServicesFeedback servicesFeedback = new ServicesFeedback();
//            servicesFeedback.setId(servicesFeedbackModel.getId());
//            servicesFeedback.setFeedback(servicesFeedbackModel.getFeedback());
//            servicesFeedback.setUser(servicesFeedbackModel.getUser());
//            servicesFeedback.setCurrentTime(servicesFeedbackModel.getCurrentTime());
//            servicesFeedback.setType(servicesFeedbackModel.getType().toString());
//            servicesFeedback.setReply(servicesFeedbackModel.getReply());
//            servicesFeedback.setIsReply(servicesFeedbackModel.getIsReply());
//
//            return servicesFeedback;
//        }
//        return null;
//    }
//    public static ServicesFeedbackModel toServiceFeedbackModel(ServicesFeedback servicesFeedback) {
//        if(servicesFeedback !=null){
//            ServicesFeedbackModel servicesFeedbackModel = new ServicesFeedbackModel();
//            servicesFeedbackModel.setId(servicesFeedback.getId());
//            servicesFeedbackModel.setFeedback(servicesFeedback.getFeedback());
//            servicesFeedbackModel.setUser(servicesFeedback.getUser());
//            servicesFeedbackModel.setCurrentTime(servicesFeedback.getCurrentTime());
//            servicesFeedbackModel.setType(FEEDBACK_TYPE.valueOf(servicesFeedback.getType()));
//            servicesFeedbackModel.setReply(servicesFeedback.getReply());
//            servicesFeedbackModel.setIsReply(servicesFeedback.getIsReply());
//
//            return servicesFeedbackModel;
//        }
//        return null;
//    }
}
