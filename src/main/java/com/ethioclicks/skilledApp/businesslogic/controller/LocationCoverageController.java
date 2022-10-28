package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.LocationCoverage;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.service.LocationCoverageService;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admin/location_coverage")
public class LocationCoverageController {

    private final UserRepo userRepo;
    private final LocationCoverageService locationCoverageService;

    public LocationCoverageController(UserRepo userRepo, LocationCoverageService locationCoverageService) {
        this.userRepo = userRepo;
        this.locationCoverageService = locationCoverageService;
    }

    @PostMapping("/create_location_coverage")
    @Operation(description = "This API receive location name and create location")
    public ResponseEntity createLocationCoverage(@Parameter(description = "Location Name")@RequestBody LocationCoverage locationCoverage,
                                          @Parameter(description = "user public Id")@RequestHeader("pid") String pid){

        if(isAdmin(pid)){

           return new ResponseEntity(locationCoverageService.saveLocationCoverage(locationCoverage), HttpStatus.OK) ;
        }else {
            throw new BadRequestException("You did not have a permission");
        }
    }
    @GetMapping("location_coverage_detail/{locationId}")
    @Operation(description = "This api receive location coverage id and return the location coverage name")
    public ResponseEntity<LocationCoverage>getLocationCoverageDetail(@Parameter(description = "locationId")@PathVariable(value = "locationId") Long locationId,
                                                              @RequestHeader(value = "pid") String pid){

        if(isAdmin(pid)){
            LocationCoverage locationCoverage = null;
            locationCoverage = locationCoverageService.getLocationById(locationId);
            return  new ResponseEntity<>(locationCoverage,HttpStatus.OK);
        }else{
            throw new BadRequestException("You did not have a permission");
        }
    }
    @PostMapping("")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This Api receive the Location coverage and update ")
    public ResponseEntity<LocationCoverage>UpdateLocationCoverage(@Parameter(description = "location coverage")@RequestBody LocationCoverage locationCoverage,
                                                           @RequestHeader(value = "pid") String pid){
        if(isAdmin(pid)){

            return  new ResponseEntity(locationCoverageService.updateLocationCoverageService(locationCoverage),HttpStatus.OK);
        }else{
            throw new BadRequestException("You did not have a permission");
        }
    }
    @DeleteMapping("/{locationId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This Api receive the Location coverage id and delete location ")
    public ResponseEntity deleteLocationCoverage(@Parameter(description = "locationId") @PathVariable("locationId")Long locationId,
                                                 @RequestHeader("pid") String pid){
        if(isAdmin(pid)){
            locationCoverageService.deleteLocationCoverage(locationId);
            return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
        }else{
            throw new BadRequestException("You did not have a permission");
        }
    }
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
