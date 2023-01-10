package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserResetModel;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.service.UserResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserResetServiceImpl implements UserResetService {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserRepo userRepo;

    @Override
    public User userNameReset(UserResetModel userResetModel) {

        List<User> users = userRepo.findByPhoneNumber(userResetModel.getPhoneNumber());
        if(users==null || users.isEmpty()) {
            throw new BadRequestException("User with phone number "+userResetModel.getPhoneNumber()+" not found");
        }
        User user = users.get(0);
        if ((userResetModel.getUserName() == null)) {
            user.setUserName(user.getUserName());
        } else {
            user.setUserName(userResetModel.getUserName());
        }

        User updatedUser = userRepo.save(user);

        return updatedUser;
    }

    @Override
    public User userPasswordReset(UserResetModel userResetModel) {

        List<User> users = userRepo.findByPhoneNumber(userResetModel.getPhoneNumber());
        if(users==null || users.isEmpty()) {
            throw new BadRequestException("User with phone number "+userResetModel.getPhoneNumber()+" not found");
        }
        User user = users.get(0);
        if (userResetModel.getUserPassword() == null) {
            user.setPassWord(user.getPassWord());
        } else {
            user.setPassWord(BCrypt.hashpw(userResetModel.getUserPassword(), BCrypt.gensalt()));
        }

        User updatedUser = userRepo.save(user);

        return updatedUser;
    }


}
