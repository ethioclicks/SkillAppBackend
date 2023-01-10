package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private  final UserRepo userRepo;

    public AdminServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isAdmin(String pid) {

        Optional<User> userByPublicId = userRepo.findByUserPublicId(pid);
        if (userByPublicId.isPresent()) {
            String role = userByPublicId.get().getRoles().stream().findFirst().get().getName();

            if (!role.equals("ADMIN")) {
                return false;
            }
            return true;
        }
        return false;
    }
}
