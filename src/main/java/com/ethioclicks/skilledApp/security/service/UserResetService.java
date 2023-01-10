package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.model.UserResetModel;

public interface UserResetService {

    User userNameReset(UserResetModel userResetModel);
    User userPasswordReset(UserResetModel userResetModel);
}
