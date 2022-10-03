package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> giveProductFeedback(Feedback feedback, String pid, Long productId, Long feedbackId);
    List<Feedback> getProductFeedbacks(Long serviceId);
}
