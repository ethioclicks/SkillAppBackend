package com.ethioclicks.skilledApp.businesslogic.service;


import com.ethioclicks.skilledApp.businesslogic.model.ReviewsModel;

import java.util.List;

public interface ReviewsService {

    List<ReviewsModel> giveReview(ReviewsModel reviewsModel, Long serviceId, String pid);

//    List<ServicesFeedbackModel> giveProductFeedback(ServicesFeedback servicesFeedback, String pid, Long serviceId, Long feedbackId);
//    List<ServicesFeedbackModel> getProductFeedbacks(Long serviceId);
}
