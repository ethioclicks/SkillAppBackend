package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.ServicesFeedback;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesFeedbackModel;

import java.util.List;

public interface FeedbackService {
    List<ServicesFeedbackModel> giveProductFeedback(ServicesFeedback servicesFeedback, String pid, Long serviceId, Long feedbackId);
    List<ServicesFeedbackModel> getProductFeedbacks(Long serviceId);
}
