package com.ethioclicks.skilledApp.businesslogic.model;

import com.ethioclicks.skilledApp.businesslogic.entity.AvailabilityHour;
import com.ethioclicks.skilledApp.businesslogic.entity.PaymentType;
import com.ethioclicks.skilledApp.businesslogic.entity.SkillCategory;
import com.ethioclicks.skilledApp.security.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ServicesModel {
    private Long id;
    private String servicePublicId;
    private SkillCategory skillCategory;
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
    private double rate;
    private PaymentType paymentType;
    private double paymentPrice;
    private double paymentRemark;
    private Date postDate;

}