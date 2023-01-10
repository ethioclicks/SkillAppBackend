package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.Role;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.NewUserDetail;

import java.util.Map;
import java.util.Set;

public interface UserRegistrationService {
    NewUserDetail saveUser(NewUserDetail user) throws Exception;
    NewUserDetail saveUser(NewUserDetail user, Set<Role> roles);

    User saveUser(User user);
    boolean isUserNameExists(String email);

    User getUser(String userPublicId);

    boolean isPhoneNumberExists(String phoneNumber);
    User updateUser(User user);
    Map<String, String> checkUserData(NewUserDetail userDetail);

    User updateStatus(User user);
}
