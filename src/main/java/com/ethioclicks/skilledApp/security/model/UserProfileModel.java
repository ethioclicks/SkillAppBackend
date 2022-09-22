package com.ethioclicks.skilledApp.security.model;

import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.security.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileModel {

    private String userPublicId;

    @NotNull(message = "First name can not be empty")
    @Size(min = 2,message = "First Name should be at least 2 characters")
    private String firstName;
    @NotNull(message = "Last name can not be empty")
    @Size(min = 2,message = "Last Name should be at least 2 characters")
    private String lastName;
    @NotBlank(message = "Phone is required")
    @NotNull(message = "Phone can not be empty")
    @Size(min = 10,message = "Phone number should be at least 10 digit")
    private String phoneNumber;
    @NotNull( message = "User Biography can not be empty")
    private String biography;
    @Size(min = 6,max = 20,message = "Password should be at least 6 and max 20 characters")
    @NotNull(message = "Password name can not be empty")
    private String userPassword;
    @NotNull(message = "Email name can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;
    @NotNull(message = "City can not be empty")
    private String city;
    @NotNull(message = "sub city can not be empty")
    private String subCity;
    private Boolean isApproved = Boolean.FALSE;
    private Boolean isSuspended = Boolean.FALSE;
    private List<Services> services;
    private Agency agency;
    @NotNull(message = "Profile Image can not be empty")
    private String profileImageUrl;

    public UserProfileModel fromUserEntity(User user) {
        if(user!=null){
            return this.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getUserName())
                    .biography(user.getBiography())
                    .phoneNumber(user.getPhoneNumber())
                    .userPassword(user.getPassWord())
                    .city(user.getCity())
                    .subCity(user.getSubCity())
                    .userPublicId(user.getUserPublicId())
                    .isApproved(user.getIsApproved())
                    .isSuspended((user.getIsSuspended()))
                    .services(user.getServices())
                    .agency(user.getAgency())
                    .profileImageUrl(user.getProfileImageUrl())
                    .build();
        }
        return null;
    }
}
