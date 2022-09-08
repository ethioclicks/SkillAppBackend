package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.repo.UserPageRepo;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserPageRepo userPageRepo;
    UserRepo userRepo;
    @Override
    public Page<User> getUserListPageable(Pageable pageable) {
        Page<User> users = userPageRepo.findAll(pageable);
        return users;
    }

    @Override
    public User updateUser(User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public UserProfileModel updateUserProfile(UserProfileModel userProfileModel) {
        Optional<User> userByUserPublicId = userRepo.findByUserPublicId(userProfileModel.getUserPublicId());
        if(userByUserPublicId.isPresent()){
            User existingUser = userByUserPublicId.get();
            existingUser.setUserPublicId(existingUser.getUserPublicId());
            existingUser.setUserName(userProfileModel.getEmail());
            existingUser.setFirstName(userProfileModel.getFirstName());
            existingUser.setLastName(userProfileModel.getLastName());
            existingUser.setPhoneNumber(userProfileModel.getPhoneNumber());
            existingUser.setPassWord(BCrypt.hashpw(userProfileModel.getUserPassword(), BCrypt.gensalt()));
            User updatedUser = userRepo.save(existingUser);
            return new UserProfileModel().fromUserEntity(updatedUser);
        }
        throw new BadRequestException("User not found");
    }
}
