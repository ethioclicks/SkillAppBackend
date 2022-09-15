package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.NewUserDetail;
import com.ethioclicks.skilledApp.security.repo.RoleRepo;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.entity.Role;

import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public NewUserDetail saveUser(NewUserDetail newUserDetail) {
        User user = new User();
        user.setUserName(newUserDetail.getEmail());
//        user.setPassWord(newUserDetail.getUserPassword());
        user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt()));
        user.setCreatedOn(new Date());
        user.setEnabled(true);
        user.setLocked(false);
        user.setIsEmailVerified(false);
        user.setRoles(roleRepo.findAllByName("USER"));
        user.setPhoneNumber(newUserDetail.getPhoneNumber());
        user.setFirstName(newUserDetail.getFirstName());
        user.setLastName(newUserDetail.getLastName());
        user.setUserPublicId(UUID.randomUUID().toString());

//        user.setIsEmailVerified(Boolean.FALSE);
        user.setBiography(newUserDetail.getBiography());
        user.setCity(newUserDetail.getCity());
        user.setSubCity(newUserDetail.getSubCity());
        user.setProfileImageUrl(newUserDetail.getProfileImageUrl());
        User savedUser = userRepo.save(user);
        if (savedUser == null) {
            return null;
        }
        return savedUser.getUserDetailModel(savedUser);


    }

    @Override
    public User saveUser(User user) {
        user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt()));
        User savedUser = userRepo.save(user);
        return savedUser;

    }

    @Override
    public NewUserDetail saveUser(NewUserDetail newUserDetail, Set<Role> roles) {
        User user = new User();
        user.setUserName(newUserDetail.getEmail());
        user.setPassWord(newUserDetail.getUserPassword());
        user.setFirstName(newUserDetail.getFirstName());
        user.setLastName(newUserDetail.getLastName());

        user.setCreatedOn(new Date());
        user.setEnabled(true);
        user.setLocked(false);

        user.setRoles(roles);



        user.setPhoneNumber(newUserDetail.getPhoneNumber());

        String publicId = UUID.randomUUID().toString();
        user.setUserPublicId(publicId);
        System.out.println(publicId);

        for(Role role:roles) {
            roleRepo.save(role);
        }

        user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt()));
        User savedUser = userRepo.save(user);
        newUserDetail.setUserPublicId(savedUser.getUserPublicId());
        return newUserDetail;
    }


    public boolean isUserNameExists(String userName){
        User userInfo=userRepo.findByUserName(userName);
        if(userInfo!=null)
            return true;
        return false;

    }

    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        List<User> users = userRepo.findByPhoneNumber(phoneNumber);
        if(users==null || users.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String userPublicId) {
        Optional<User> userByUserPublicId = userRepo.findByUserPublicId(userPublicId);
        if(userByUserPublicId.isPresent()){
            return userByUserPublicId.get();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public Map<String, String> checkUserData(NewUserDetail userDetail) {
        Map<String, String> errors = new HashMap<>();
        if(userDetail.getPhoneNumber()==null) {
            errors.put("Phone", "Phone not found");
        }
        if(userDetail.getEmail()==null) {
            errors.put("Email", "Email not found");
        }
        if(!errors.isEmpty()) {
            return errors;
        }

        User usersByEmail = userRepo.findByUserName(userDetail.getEmail());
        if(usersByEmail != null) {
            errors.put("Email", "Email is used");
        }

        List<User> usersByPhoneNumber = userRepo.findByPhoneNumber(userDetail.getPhoneNumber());
        if(!usersByPhoneNumber.isEmpty()) {
            errors.put("Phone", "Phone number is used");
        }

        return errors;
    }


}
