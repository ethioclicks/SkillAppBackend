package com.ethioclicks.skilledApp.security.controller;


import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.AdminService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    AdminService adminService;


    @GetMapping("/userList")
    @Operation(description = "This API is for getting List of Users")
    Page<User> getUsers(@RequestHeader(value = "pid", required = true) String pid, @PageableDefault() Pageable pageable) {
        if(adminService.isAdmin(pid)){

            return userService.getUserListPageable(pageable);
        }

        return Page.empty();
    }
    @PostMapping("/updateIsEnable")
    @Operation(description = "This API receive User's Public Id as Header Parameter and then update IsEnable value")
    ResponseEntity<String> updateIsEnabled(@Parameter(description = "User's Public Id") @RequestHeader(value = "pid", required = true) String pid, @RequestParam("upid") String upid) throws Exception {
        if(adminService.isAdmin(pid)){
            Optional<User> userByPublicId = userRepo.findByUserPublicId(upid);

            if (userByPublicId.isPresent()) {
                User user = userByPublicId.get();

                if (user.isEnabled() == true) {
                    user.setEnabled(false);
                } else {
                    user.setEnabled(true);
                }
                User updatedUser = userService.updateUser(user);
                if (updatedUser != null) {
                    return new ResponseEntity("updated", null, HttpStatus.OK);
                }
            }
            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity( "Don't Have  Access", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateIsLocked")
    @Operation(description = "This API receive User's Public Id as Header Parameter and then update IsEnable value")
    ResponseEntity<String> updateLocked (@Parameter(description = "User's Public Id ") @RequestHeader(value = "pid", required = true) String pid, @RequestParam("upid") String upid) throws Exception {
        if(adminService.isAdmin(pid)) {
            Optional<User> userByPublicId = userRepo.findByUserPublicId(upid);

            if (userByPublicId.isPresent()) {
                User user = userByPublicId.get();
                if (user.isLocked() == true) {
                    user.setLocked(false);
                } else {
                    user.setLocked(true);
                }
                User updatedUser = userService.updateUser(user);
                if (updatedUser != null) {
                    return new ResponseEntity("updated", null, HttpStatus.OK);
                }

            }
            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Don't Have  Access", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{phoneNo}")
    @Operation(description = "This API receive User's using user Phone Number, User's Information as Request Body then change/update User's Info and return the user's profile.")
    ResponseEntity<UserProfileModel> updateUserProfile(@Parameter(description = "User's PhoneNumber") @RequestHeader(value = "pid", required = true) String pid, @PathVariable("phoneNo") String phoneNo) {
        if (adminService.isAdmin(pid)) {

            Optional<User> userByPhoneNo = userRepo.findUserByPhoneNumber(phoneNo);

            if (userByPhoneNo.isPresent()) {
                return new ResponseEntity(userByPhoneNo.get(), null, HttpStatus.OK);

            }else{
                return null;
            }
        }
        return new ResponseEntity("Don't Have  Access", HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/{phoneNo}")
    @Operation(description = "This API receive User's using user Phone Number, User's Information as Request Body then change/update User's Info and return the user's profile.")
    ResponseEntity<UserProfileModel> updateUserProfile(@Parameter(description = "User's PhoneNumber") @RequestHeader(value = "pid", required = true) String pid,   @RequestBody UserProfileModel userProfileModel) {

        if (adminService.isAdmin(pid)) {

            Optional<User> userByPublicId = userRepo.findByUserPublicId(userProfileModel.getUserPublicId());

            if(userByPublicId.isPresent()) {

                String newPhoneNo = userProfileModel.getPhoneNumber();
                if (!newPhoneNo.equals(userProfileModel.getPhoneNumber())){
                    Optional<User> userPhoneNo = userRepo.findUserByPhoneNumber(newPhoneNo);

                    if (userPhoneNo.isPresent()) {
                        return new ResponseEntity("phone no Already Exits", HttpStatus.FOUND);
                    }
                }
                return new ResponseEntity(userService.updateUserProfile( userProfileModel), null, HttpStatus.OK);
            }
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Don't Have  Access", HttpStatus.NOT_FOUND);
    }
//    @PostMapping("shop/shopVerification")
//    @Operation(description = "This API receive User's Public Id as Header Parameter and then update IsEnable value")
//    ResponseEntity<String> verifyShop (@Parameter(description = "User's Public Id ") @RequestHeader(value = "pid", required = true) String pid, @RequestParam("shopPublicId") String shopPublicId) throws Exception {
//        if(adminService.isAdmin(pid)) {
//            Optional<Shop> shopByPublicId = shopRepo.findByShopPublicId(shopPublicId);
//
//            if (shopByPublicId.isPresent()) {
//               shopService.verifyShop(shopByPublicId.get(), shopPublicId);
//                return new ResponseEntity("Verification Complete", HttpStatus.OK);
//            }
//            return new ResponseEntity("shop Not Found", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity("Don't Have  Access", HttpStatus.NOT_FOUND);
//    }
}

