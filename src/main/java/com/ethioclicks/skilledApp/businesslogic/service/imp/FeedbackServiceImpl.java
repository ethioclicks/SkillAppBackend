package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.entity.ServicesFeedback;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesFeedbackModel;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesFeedBackRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.FeedbackService;
import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final UserRegistrationService userRegistrationService;
    private final ServicesRepo servicesRepo;
    private final ServicesFeedBackRepo servicesFeedBackRepo;

    public FeedbackServiceImpl(UserRegistrationService userRegistrationService, ServicesRepo servicesRepo, ServicesFeedBackRepo servicesFeedbackRepo) {
        this.userRegistrationService = userRegistrationService;
        this.servicesRepo = servicesRepo;
        this.servicesFeedBackRepo = servicesFeedbackRepo;
    }

    @Override
    public List<ServicesFeedbackModel> giveProductFeedback(ServicesFeedback servicesFeedback, String pid, Long serviceId, Long feedbackId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        User user = userRegistrationService.getUser(pid);
        Services services = servicesRepo.getServiceById(serviceId);
        if (user != null) {
            if (services != null) {
                if (feedbackId != null) {
                    Optional<ServicesFeedback> replyOptional = servicesFeedBackRepo.findById(feedbackId);
                    ServicesFeedback existingFeedback = replyOptional.orElseThrow(() -> new BadRequestException("Feedback with the give id not found"));
                    servicesFeedback.setUser(user);
                    servicesFeedback.setServices(services);
                    servicesFeedback.setCurrentTime(LocalDateTime.now());
                    servicesFeedback.setIsReply(true);

                    existingFeedback.setReply(servicesFeedback);
                    servicesFeedBackRepo.save(servicesFeedback);
                    servicesFeedBackRepo.save(existingFeedback);

                } else {
                    servicesFeedback.setUser(user);
                    servicesFeedback.setServices(services);
                    servicesFeedback.setCurrentTime(LocalDateTime.now());

                    servicesFeedBackRepo.save(servicesFeedback);
                }
                return getProductFeedbacks(serviceId);
            } else {
                throw new BadRequestException("Services not found ", new Exception("Services not found"));
            }

        } else {
            throw new BadRequestException("User not found", new Exception("User not found"));
        }
    }
    @Override
    public List<ServicesFeedbackModel> getProductFeedbacks(Long serviceId) {
        Services services =  servicesRepo.getServiceById(serviceId);
        if(services!=null){
            List<ServicesFeedback> servicesFeedbacksList = servicesFeedBackRepo.findAllByServicesAndIsReply(services, false);
            if(servicesFeedbacksList !=null && !servicesFeedbacksList.isEmpty()){
                return servicesFeedbacksList
                        .stream()
                        .map(Mapper::toServiceFeedbackModel)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
