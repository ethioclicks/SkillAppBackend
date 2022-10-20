package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;
import com.ethioclicks.skilledApp.businesslogic.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    private final ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }
    @PostMapping("services/create_service")
    @Operation(description = "This API receive services Information and Create Service detail ")
    ResponseEntity createService(@Parameter (description = "Service's Information" )@RequestBody ServicesModel servicesModel,
                                 @Parameter(description = "User's Public Id")@RequestHeader(value = "userPublicId") String pid)throws Exception{
        ServicesModel saveServices = servicesService.saveService(servicesModel, pid);
        return new ResponseEntity(saveServices, HttpStatus.OK);
    }

    @GetMapping("public/service/service_detail_by_publicId/{servicePublicId}")
    @Operation(description = "This API accept service Public id  and return the service's info")
    public ResponseEntity<ServicesModel> getServiceDetail(@Parameter(description = "service Public Id") @PathVariable("servicePublicId") String servicePublicId){
        ServicesModel servicesModel = null;
        servicesModel = servicesService.getServiceByPublicId(servicePublicId);
        return new ResponseEntity(servicesModel, HttpStatus.OK);
    }
    @DeleteMapping( "service/{servicePublicId}" )
    @SecurityRequirement( name = "bearerAuth" )
    @Operation( description = "This API accept restaurantId and delete the restaurant" )
    public ResponseEntity deleteServices(@Parameter( description = "service publicId" ) @PathVariable( "servicePublicId" ) String servicePublicId) {


        servicesService.deleteProduct(servicePublicId);
        return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
    }
    @GetMapping("service/service_by_owner")
    @SecurityRequirement(name="bearerAuth")
    @Operation(description = "This is API return all the services by the owner Public Id")
    public ResponseEntity<List<Services>>listOfServicesByOwnerPublicId(@RequestHeader(value = "pid") String pid){
        return new ResponseEntity<>(servicesService.getServicesByOwnerPublicId(pid), HttpStatus.OK);
    }
    @GetMapping("public/services/all")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API return all the service's info")
    public ResponseEntity<Page<Services>> getAllServices(@Parameter(description = "page")
                                                  @RequestParam("page") Integer page,
                                                  @Parameter(description = "size") @RequestParam("size") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity(servicesService.getAllServices(pageable), HttpStatus.OK);
    }





}
