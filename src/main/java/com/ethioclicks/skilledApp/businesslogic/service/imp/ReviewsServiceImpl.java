package com.ethioclicks.skilledApp.businesslogic.service.imp;



import com.ethioclicks.skilledApp.businesslogic.entity.Reviews;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.model.ReviewsModel;
import com.ethioclicks.skilledApp.businesslogic.repo.ReviewsRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ReviewsService;

import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final UserRegistrationService userRegistrationService;
    private final ServicesRepo servicesRepo;
    private final ReviewsRepo reviewsRepo;

    public ReviewsServiceImpl(UserRegistrationService userRegistrationService, ServicesRepo servicesRepo, ReviewsRepo servicesFeedbackRepo) {
        this.userRegistrationService = userRegistrationService;
        this.servicesRepo = servicesRepo;
        this.reviewsRepo = servicesFeedbackRepo;
    }

    @Override
    public List<ReviewsModel> giveReview(ReviewsModel reviewsModel, Long serviceId, String pid) {
        User user = userRegistrationService.getUser(pid);
        Services services = servicesRepo.getServiceById(serviceId);
        if (user != null) {
            if(services !=null){

                ////////////////rating
//                DecimalFormat df = new DecimalFormat("#.#");
//                Reviews serviceRating = reviewsRepo.findProductRatingByProductAndUser(serviceId, user);


                reviewsModel.setUser(user);
                reviewsModel.setRating(reviewsModel.getRating());
                reviewsModel.setCurrentTime(LocalDateTime.now());
                List<ReviewsModel> reviews = (Arrays.asList(reviewsModel)) ;
                services.setReviews(reviews.stream().map(Mapper::toReviewEntity).collect(Collectors.toList()));

                servicesRepo.save(services);
            }
        }
        return null;
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
