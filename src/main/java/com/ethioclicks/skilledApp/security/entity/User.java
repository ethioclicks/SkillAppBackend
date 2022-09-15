package com.ethioclicks.skilledApp.security.entity;

import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.model.NewUserDetail;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;
    @Column(name = "USER_NAME")
    String userName;
    @Column(name = "PASSWORD")
    String passWord;
    @Column(name = "IS_LOCKED")
    boolean isLocked;
    @Column(name = "IS_ENABLED")
    boolean isEnabled;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="ROLE_USER",joinColumns=@JoinColumn(name="USER_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
    @JsonIgnoreProperties("users")
    private Set<Role> roles=new HashSet<>();

    @Column(name = "CREATED_ON")
    Date createdOn;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIOGRAPHY", length = 1000)
    private String biography;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "USER_PUBLIC_ID")
    private String userPublicId;
    @Column(name = "CITY")
    private String city;
    @Column(name = "SUB_CITY")
    private String subCity;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "VERIFIED_EMAIL")
    private String verifiedEmail;
    @Column(name = "IS_EMAIL_VERIFIED")
    private Boolean isEmailVerified = Boolean.FALSE;
    @Column(name = "IS_APPROVED")
    private Boolean isApproved = Boolean.FALSE;
    @Column(name = "IS_SUSPENDED")
    private Boolean isSuspended = Boolean.FALSE;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Services> services;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="AGENCY_ID")
    private Agency agency;
    public User fromUserProfile(UserProfileModel userProfileModel){
        return this.builder()
                .userName(userProfileModel.getEmail())
                .passWord(userProfileModel.getUserPassword())
                .firstName(userProfileModel.getFirstName())
                .lastName(userProfileModel.getLastName())
                .phoneNumber(userProfileModel.getPhoneNumber())
                .userPublicId(userProfileModel.getUserPublicId())
                .biography(userProfileModel.getBiography())
                .city(userProfileModel.getCity())
                .subCity(userProfileModel.getStreet())
                .isApproved(userProfileModel.getIsApproved())
                .isSuspended(userProfileModel.getIsSuspended())
                .profileImageUrl(userProfileModel.getProfileImageUrl())
                .services(userProfileModel.getServices())
                .build();
    }
    public NewUserDetail getUserDetailModel(User savedUser) {
        return NewUserDetail.builder()
                .userPublicId(this.userPublicId)
                .email(this.userName)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .userPassword(this.passWord)
                .phoneNumber(this.phoneNumber)
                .city(this.city)
                .subCity(this.subCity)
                .biography(this.biography)
                .isSuspended(this.isSuspended)
                .isApproved(this.isApproved)
                .profileImageUrl(this.profileImageUrl)
                .isEmailVerified(this.isEmailVerified)
                .verifiedEmail(this.verifiedEmail)
                .build();
    }
}
