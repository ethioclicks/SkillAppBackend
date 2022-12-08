package com.ethioclicks.skilledApp.security.model;

import com.ethioclicks.skilledApp.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenPayLoad {
    private String token;
    private String pid;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;

    private String verifiedEmail;
    private Boolean isEmailVerified;

    private Boolean isApproved;

    private Boolean isSuspended;

    public TokenPayLoad(String token, User user){
        this.token=token;

        if(user!=null){
            this.pid=user.getUserPublicId();
            this.firstName=user.getFirstName();
            this.lastName=user.getLastName();
            this.username=user.getUserName();
            this.phone = user.getPhoneNumber();
            this.verifiedEmail = user.getVerifiedEmail();
            this.isEmailVerified = user.getIsEmailVerified();
            this.isApproved = user.getIsApproved();
            this.isSuspended= user.getIsSuspended();
        }


    }
}
