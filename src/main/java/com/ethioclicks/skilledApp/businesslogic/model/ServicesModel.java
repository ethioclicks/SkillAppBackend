package com.ethioclicks.skilledApp.businesslogic.model;

import com.ethioclicks.skilledApp.businesslogic.entity.*;
import com.ethioclicks.skilledApp.security.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class ServicesModel {
    private Long id;
    private String tag;
    private String servicePublicId;
    private Long skillCategory;
    private String description;
    private Integer yearInService;
    private Date serviceRegisteredDate;
    private String skills;
    private String imageUrl;
    private User user;
    private String locationCoverage;
    private String  availabilityTypeId;
    private Integer numberOfServicesCompleted;
    private List<AvailabilityHour> availabilityHours;
    private List<Reviews> reviews;
    private List<Projects> projects;
    private List<ServiceImage>serviceImages;
    private Long paymentType;
    private double paymentPrice;
    private String paymentRemark;
    private LocalDateTime postDate;

}
