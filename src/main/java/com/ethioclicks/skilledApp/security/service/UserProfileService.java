package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {


    UserProfileModel getUserProfile(String userPublicId);

    UserProfileModel updateUserProfile(String pid, UserProfileModel userProfileModel);
}
