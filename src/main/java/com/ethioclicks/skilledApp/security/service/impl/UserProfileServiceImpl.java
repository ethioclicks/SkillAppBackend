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
            User exisitingUser = userByPublicId.get();
            exisitingUser.setUserPublicId(pid);
            exisitingUser.setUserName(userProfileModel.getEmail());
            exisitingUser.setFirstName(userProfileModel.getFirstName());
            exisitingUser.setLastName(userProfileModel.getLastName());
            exisitingUser.setPhoneNumber(userProfileModel.getPhoneNumber());
            exisitingUser.setCity(userProfileModel.getCity());
            exisitingUser.setSubCity(userProfileModel.getSubCity());
            exisitingUser.setBiography(userProfileModel.getBiography());
            exisitingUser.setProfileImageUrl(userProfileModel.getProfileImageUrl());
            User updatedUser = userRepo.save(exisitingUser);
            return new UserProfileModel().fromUserEntity(updatedUser);
        }
        throw new BadRequestException("Profile not found");
    }
}
