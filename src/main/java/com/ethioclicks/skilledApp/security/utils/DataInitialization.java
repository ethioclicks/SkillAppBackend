//package com.ethioclicks.skilledApp.security.utils;
//
//import com.ethioclicks.skilledApp.businesslogic.entity.*;
//import com.ethioclicks.skilledApp.businesslogic.enums.AvailabilityType;
//import com.ethioclicks.skilledApp.businesslogic.enums.DAY_OF_WEEK;
//import com.ethioclicks.skilledApp.businesslogic.enums.FEEDBACK_TYPE;
//import com.ethioclicks.skilledApp.businesslogic.enums.PAYMENT_TYPE_ENUM;
//import com.ethioclicks.skilledApp.businesslogic.model.AgencyModel;
//import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
//import com.ethioclicks.skilledApp.businesslogic.service.*;
//import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
//import com.ethioclicks.skilledApp.security.entity.Role;
//import com.ethioclicks.skilledApp.security.entity.User;
//import com.ethioclicks.skilledApp.security.enums.RoleEnum;
//import com.ethioclicks.skilledApp.security.model.NewUserDetail;
//import com.ethioclicks.skilledApp.security.repo.RoleRepo;
//import com.ethioclicks.skilledApp.security.repo.UserRepo;
//import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.util.*;
//
//
//@Component
//public class DataInitialization {
//    //
//    private final UserRegistrationService userRegistrationService;
//    private final ServicesService servicesService;
//    private final SkillCategoryService skillCategoryService;
//    private final FeedbackService feedbackService;
//    private final RateService rateService;
//    private final AgencyService agencyService;
//    private final RoleRepo roleRepo;
//    private final UserRepo userRepo;
//
//    public DataInitialization(UserRegistrationService userRegistrationService, ServicesService servicesService, SkillCategoryService skillCategoryService, FeedbackService feedbackService, RateService rateService, AgencyService agencyService, RoleRepo roleRepo, UserRepo userRepo) {
//        this.userRegistrationService = userRegistrationService;
//
//        this.servicesService = servicesService;
//        this.skillCategoryService = skillCategoryService;
//        this.feedbackService = feedbackService;
//        this.rateService = rateService;
//        this.agencyService = agencyService;
//        this.roleRepo = roleRepo;
//        this.userRepo = userRepo;
//    }
//    @PostConstruct
//    void init(){
//
////        SkillCategory category = new SkillCategory(1L,"Mechanic","Motor Technician","Professional Motor Technician ");
////        skillCategoryService.saveCategory(category);
////
////        Role userRole=new Role(RoleEnum.ADMIN);
////        Set<Role> userRoleSet=new HashSet<>();
////        userRoleSet.add(userRole);
////        roleRepo.save(userRole);
//
////        String userUUID = UUID.randomUUID().toString();
////        NewUserDetail newUserDetail = new NewUserDetail();
////        newUserDetail.setUserPublicId(userUUID);
////        newUserDetail.setFirstName("alemayew");
////        newUserDetail.setLastName("tesma");
////        newUserDetail.setPhoneNumber("0987542136");
////        newUserDetail.setUserPassword("123456");
////        newUserDetail.setEmail("userUser@gmail.com");
////        newUserDetail.setCity("Addis Ababa");
////        newUserDetail.setSubCity("Yeka");
////        newUserDetail.setVerifiedEmail("userUser@gmail.com");
////        newUserDetail.setIsEmailVerified(true);
////        newUserDetail.setIsApproved(true);
////        newUserDetail.setIsSuspended(false);
////        Set<Role> userRoleSet= roleRepo.findAllByName("USER");
////        NewUserDetail userDetail = userRegistrationService.saveUser(newUserDetail, userRoleSet);
////        NewUserDetail adminDetail1 = userRegistrationService.saveUser(newAdminDetail, adminRoleset);
//
////
////        User loggedInUser= userRegistrationService.getUser(userDetail.getUserPublicId());
//
////       List<AvailabilityHour> availabilityHours = new ArrayList<>();
////       AvailabilityHour availabilityHour = new AvailabilityHour();
////       availabilityHour.setStartingHour("02:00");
////       availabilityHour.setEndingHour("11:00");
////       availabilityHour.setDay_of_week(DAY_OF_WEEK.MONDAY);
////       availabilityHours.add(availabilityHour);
////
////       AvailabilityHour availabilityHour2 = new AvailabilityHour();
////       availabilityHour2.setStartingHour("02:00");
////       availabilityHour2.setEndingHour("11:00");
////       availabilityHour2.setDay_of_week(DAY_OF_WEEK.TUESDAY);
////       availabilityHours.add(availabilityHour2);
////
////       PaymentType paymentType = new PaymentType();
////       paymentType.setName(PAYMENT_TYPE_ENUM.HOURLY.paymentTypeName());
////
////       ServicesModel services = new ServicesModel();
//////       services.setServicePublicId(UUID.randomUUID().toString());
//////       services.setSkillCategory(category);
////       services.setDescription("we give services in all kinds of the car techniques");
////       services.setYearInService(2);
////
//////       Date registrationDate = parseDate("2021/02/10");
//////       services.setServiceRegisteredDate(registrationDate);
////       services.setSkills("electrical,mechanic ");
////       services.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWb1FODDj4iwUZPqYxNpQGV5hbl5Mus_TtkmraoPLg&s");
////       User loggedInUser  = userRepo.findByUserName("userUser@gmail.com");
//////       services.setUser(loggedInUser);
////       services.setLocationCoverage("megenagna, piazza, cmc");
////       services.setAvailabilityTypeId(AvailabilityType.APPOINTMENT.name());
////       services.setNumberOfServicesCompleted(3);
////       services.setAvailabilityHours(availabilityHours);
////       services.setPaymentType(paymentType);
////       services.setPaymentPrice(200);
////       services.setPaymentRemark("Pay cash as soon as the work done");
//////       services.setPostDate(LocalDateTime.now());
////       ServicesModel service1 = servicesService.saveService(services, loggedInUser.getUserPublicId());
//        User loggedInUser  = userRepo.findByUserName("userUser@gmail.com");
////        AgencyModel agency = new AgencyModel();
//////        agency.setAgencyPublicId(UUID.randomUUID().toString());
////        agency.setDescription("The best agency where to find mechanics, house cleaner, electricians");
////        agency.setCity("Addis Ababa");
////        agency.setStreet("yeka, werda 13");
////        agency.setPhone("095678967845");
////        agency.setEmail("agency@gmail.com");
////        agency.setOwner(loggedInUser);
////        agency.setPostDate(LocalDateTime.now());
////
////        agencyService.saveAgency(agency, loggedInUser.getUserPublicId());
//
////        ServicesModel services = servicesService.getServiceByPublicId("ec902d55-251a-4231-aefc-93bc12e626e9") ;
////        ServicesFeedback newServiceServicesFeedback = new ServicesFeedback();
////        newServiceServicesFeedback.setType(FEEDBACK_TYPE.USER_FEEDBACK.name());
////        newServiceServicesFeedback.setServices(Mapper.toServiceEntity(services));
////        newServiceServicesFeedback.setFeedback("This shop is a the best shop ever");
////        newServiceServicesFeedback.setCurrentTime(LocalDateTime.now());
////        feedbackService.giveProductFeedback(newServiceServicesFeedback,loggedInUser.getUserPublicId(), services.getId(), newServiceServicesFeedback.getId());
////        rateService.rateService(4,Mapper.toServiceEntity(services),loggedInUser);
//
//    }
//}