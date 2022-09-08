package com.ethioclicks.skilledApp.security.entity;

import com.ethioclicks.skilledApp.security.model.NewUserDetail;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.utils.Util;
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
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "USER_PUBLIC_ID")
    private String userPublicId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ADDRESS_ID")
    private UserAddress address;

//  Security Check Questions Answer
    @Column(name = "QUESTIONSANDANSWERS")
    private String questionsAndAnswers;

    @Column(name = "VERIFIED_EMAIL")
    private String verifiedEmail;
    @Column(name = "IS_EMAIL_VERIFIED")
    private Boolean isEmailVerified = Boolean.FALSE;



    public User fromUserProfile(UserProfileModel userProfileModel){
        return this.builder()
                .userName(userProfileModel.getEmail())
                .passWord(userProfileModel.getUserPassword())
                .firstName(userProfileModel.getFirstName())
                .lastName(userProfileModel.getLastName())
                .address(userProfileModel.getAddress())
                .userPublicId(userProfileModel.getUserPublicId())
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
                .address(this.address)
                .questionsAndAnswers(Util.stringToArray(this.questionsAndAnswers))
                .isEmailVerified(this.isEmailVerified)
                .verifiedEmail(this.verifiedEmail)
                .build();
    }
}
