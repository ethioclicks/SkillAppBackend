package com.ethioclicks.skilledApp.businesslogic.entity;

import com.ethioclicks.skilledApp.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name = "SERVICE_PUBLIC_ID")
    private String servicePublicId;
    @Lob
    @Column(name = "TAG", length = 1000 )
    private String tag;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SKILL_CATEGORY_ID")
    private SkillCategory skillCategory;
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    @Column(name = "YEAR_IN_SERVICE")
    private Integer yearInService;
    @Column(name = "SERVICE_REGISTERED_DATE")
    private Date serviceRegisteredDate;
    @Column(name = "SKILLS", length = 1000)
    private String skills;
    @Column (name = "IMAGE_URL")
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("services")
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "LOCATION_COVERAGE", length = 1000)
    private String locationCoverage;
    @Column(name = "AVAILABILITY_TYPE_ID")
    private String  availabilityTypeId;
    @Column(name = "NUMBER_OF_SERVICES_COMPLETED")
    private Integer numberOfServicesCompleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AVAILABILITY_HOUR_ID")
    private List<AvailabilityHour> availabilityHours;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICES_ID" )
    private List<Projects> projects;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICES_ID")
    private List<ServiceImage>serviceImages;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "SERVICES_ID")
    private List<Reviews> reviews;
    @Transient
    private int usersRated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_TYPE_ID")
    private PaymentType paymentType;
    @Column(name = "PAYMENT_PRICE")
    private double paymentPrice;
    @Column(name = "PAYMENT_REMARK")
    private String paymentRemark;
    @Column(name = "POST_DATE")
    private LocalDateTime postDate;
}
