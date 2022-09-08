package com.ethioclicks.skilledApp.security.model;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.entity.UserAddress;
import com.ethioclicks.skilledApp.security.utils.Util;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileModel {

    private String userPublicId;

    @NotNull(message = "First name can not be empty")
    @Size(min = 2,message = "Full name should be at least 2 characters")
    private String firstName;
    @NotNull(message = "Last name can not be empty")
    @Size(min = 2,message = "Full name should be at least 2 characters")
    private String lastName;
    @NotNull(message = "Phone can not be empty")
    @Size(min = 10,message = "Phone number should be at least 10 digit")
    private String phoneNumber;
    @Size(min = 6,max = 20,message = "Password should be at least 6 and max 20 characters")
    @NotNull(message = "Password name can not be empty")
    private String userPassword;
    @NotNull(message = "Email name can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;
    @NotNull(message = "User Address can not be empty")
    private UserAddress address;
    @NotNull(message = "Questions and Answer can not be empty")
    private String questionsAndAnswers[];

    public UserProfileModel fromUserEntity(User user) {
        if(user!=null){
            return this.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getUserName())
                    .phoneNumber(user.getPhoneNumber())
                    .userPassword(user.getPassWord())
                    .address(user.getAddress())
                    .userPublicId(user.getUserPublicId())
                    .questionsAndAnswers(Util.stringToArray(user.getQuestionsAndAnswers()))
                    .build();
        }
        return null;
    }
}
