package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getUserListPageable(Pageable pageable);

    User updateUser(User user);

    UserProfileModel updateUserProfile(UserProfileModel userProfileModel);
}
