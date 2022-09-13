package com.ethioclicks.skilledApp.businesslogic.entity;


import com.ethioclicks.skilledApp.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SERVICES")
@Data
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private SkillCategory skillCategory;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "YEAR_IN_SERVICE")
    private Integer yearInService;
    @Column(name = "SERVICE_REGISTERED_DATE")
    private Date serviceRegisteredDate;
    @Column(name = "SKILL")
    private String skill;
    @Column (name = "IMAGE_URL")
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private List<User> users;

    @Column(name = "LOCATION_COVERAGE")
    private String locationCoverage;

    @Column(name = "AVAILABILITY_TYPE_ID")
    private String  availabilityTypeId;

    @Column(name = "NUMBER_OF_SERVICES_COMPLETED")
    private Integer numberOfServicesCompleted;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SERVICE_ID")

    List<AvailabilityHour> availabilityHours;
    @Column(name = "RATE")
    private double rate;

    @Column(name = "USERS_RATED")
    private int usersRated;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    @Column(name = "PAYMENT_PRICE")
    private double paymentPrice;
    @Column(name = "PAYMENT_REMARK")
    private double paymentRemark;


}
