package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.CustomUserDetails;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName){
        User userUserName = userRepo.findByUserName(userName);
        if(userUserName == null){
//            throw new UsernameNotFoundException("Could not find user");
            log.error("Error occured when User name: {} is trying to access",userName);
        }
        return new CustomUserDetails(userUserName);
    }
}
