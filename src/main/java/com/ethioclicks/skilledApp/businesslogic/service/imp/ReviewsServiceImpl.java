package com.ethioclicks.skilledApp.businesslogic.service.imp;



import com.ethioclicks.skilledApp.businesslogic.repo.ReviewsRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ReviewsService;

import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final UserRegistrationService userRegistrationService;
    private final ServicesRepo servicesRepo;
    private final ReviewsRepo servicesFeedBackRepo;

    public ReviewsServiceImpl(UserRegistrationService userRegistrationService, ServicesRepo servicesRepo, ReviewsRepo servicesFeedbackRepo) {
        this.userRegistrationService = userRegistrationService;
        this.servicesRepo = servicesRepo;
        this.servicesFeedBackRepo = servicesFeedbackRepo;
    }
//@Transactional
//    @Override
//    public List<ServicesFeedbackModel> giveProductFeedback(ServicesFeedback servicesFeedback, String pid, Long serviceId, Long feedbackId) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        User user = userRegistrationService.getUser(pid);
//        Services services = servicesRepo.getServiceById(serviceId);
//        if (user != null) {
//            if (services != null) {
//                if (feedbackId != null) {
//                    Optional<ServicesFeedback> replyOptional = servicesFeedBackRepo.findById(feedbackId);
//                    ServicesFeedback existingFeedback = replyOptional.orElseThrow(() -> new BadRequestException("Feedback with the give id not found"));
//                    servicesFeedback.setUser(user);
//                    servicesFeedback.setServices(services);
//                    servicesFeedback.setCurrentTime(LocalDateTime.now());
//                    servicesFeedback.setIsReply(true);
//
//                    existingFeedback.setReply(servicesFeedback);
//                    servicesFeedBackRepo.save(servicesFeedback);
//                    servicesFeedBackRepo.save(existingFeedback);
//
//                } else {
//                    servicesFeedback.setUser(user);
//                    servicesFeedback.setServices(services);
//                    servicesFeedback.setCurrentTime(LocalDateTime.now());
//
//                    servicesFeedBackRepo.save(servicesFeedback);
//                }
//                return getProductFeedbacks(serviceId);
//            } else {
//                throw new BadRequestException("Services not found ", new Exception("Services not found"));
//            }
//
//        } else {
//            throw new BadRequestException("User not found", new Exception("User not found"));
//        }
//    }
//    @Override
//    public List<ServicesFeedbackModel> getProductFeedbacks(Long serviceId) {
//        Services services =  servicesRepo.getServiceById(serviceId);
//        if(services!=null){
//            List<ServicesFeedback> servicesFeedbacksList = servicesFeedBackRepo.findAllByServicesAndIsReply(services, false);
//            if(servicesFeedbacksList !=null && !servicesFeedbacksList.isEmpty()){
//                return servicesFeedbacksList
//                        .stream()
//                        .map(Mapper::toServiceFeedbackModel)
//                        .collect(Collectors.toList());
//            }
//        }
//        return null;
//    }

}
