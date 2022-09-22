package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserProfileService;
import com.ethioclicks.skilledApp.security.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserProfileModel getUserProfile(String userPublicId) {
        Optional<User> userByPublicId = userRepo.findByUserPublicId(userPublicId);
        if(userByPublicId.isPresent()){
            User user = userByPublicId.get();
            return new UserProfileModel().fromUserEntity(user);
        }
        return null;
    }

    @Override
    public UserProfileModel updateUserProfile(String pid, UserProfileModel userProfileModel) {
        Optional<User> userByPublicId = userRepo.findByUserPublicId(pid);
        if (userByPublicId.isPresent()) {
            User existingUser = userByPublicId.get();
            existingUser.setUserPublicId(pid);
            existingUser.setUserName(userProfileModel.getEmail());
            existingUser.setFirstName(userProfileModel.getFirstName());
            existingUser.setLastName(userProfileModel.getLastName());
            existingUser.setPhoneNumber(userProfileModel.getPhoneNumber());
            existingUser.setCity(userProfileModel.getCity());
            existingUser.setSubCity(userProfileModel.getSubCity());
            existingUser.setBiography(userProfileModel.getBiography());
            existingUser.setProfileImageUrl(userProfileModel.getProfileImageUrl());
            User updatedUser = userRepo.save(existingUser);
            return new UserProfileModel().fromUserEntity(updatedUser);
        }
        throw new BadRequestException("Profile not found");
    }
}
