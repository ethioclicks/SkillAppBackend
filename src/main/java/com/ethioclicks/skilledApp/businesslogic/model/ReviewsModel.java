package com.ethioclicks.skilledApp.businesslogic.model;


import com.ethioclicks.skilledApp.businesslogic.entity.Reviews;
import com.ethioclicks.skilledApp.businesslogic.enums.FEEDBACK_TYPE;
import com.ethioclicks.skilledApp.security.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewsModel {

    Long id;
    String feedback;

    private Integer rating;
    LocalDateTime currentTime;
    FEEDBACK_TYPE type;
    User user;
    Reviews replay;
    Boolean isReply = false;
}
