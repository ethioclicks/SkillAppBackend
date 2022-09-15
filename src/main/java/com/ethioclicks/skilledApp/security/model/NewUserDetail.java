package com.ethioclicks.skilledApp.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDetail{

    private String userPublicId;

    @NotNull(message = "First name can not be empty")
    @Size(min = 2,message = "Full name should be at least 2 characters")
    private String firstName;
    @NotNull(message = "Last name can not be empty")
    @Size(min = 2,message = "Full name should be at least 2 characters")
    private String lastName;
    @NotBlank(message = "Phone is required")
    @NotNull(message = "Phone can not be empty")
    @Size(min = 10,message = "Phone number should be at least 10 digit")
    private String phoneNumber;
    @NotNull( message = "User Biography can not be empty")
    private String biography;
    @Size(min = 6,max = 50,message = "Password should be at least 6 and max 20 characters")
    @NotNull(message = "Password name can not be empty")
    private String userPassword;
    @NotBlank(message = "Email is required")
    @NotNull(message = "Email name can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;
    @NotNull(message = "City can not be empty")
    private String city;
    @NotNull(message = "sub city can not be empty")
    private String subCity;
    private Boolean isApproved = Boolean.FALSE;

    private Boolean isSuspended = Boolean.FALSE;
    @NotNull(message = "Profile Image can not be empty")
    private String profileImageUrl;
    private String verifiedEmail;
    private Boolean isEmailVerified = Boolean.FALSE;

}
