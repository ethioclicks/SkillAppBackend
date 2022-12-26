package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class SearchServiceController {
    private final SearchService searchService;

    public SearchServiceController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/service/service-auto-complete")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive Params Key 'keyword' and give suggestions")
    public ResponseEntity<Page<Services>> searchServices(@Parameter(description = "keyword")
                                                             @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword
                                                            ,@RequestParam(name = "rate", required = false) Integer rate) {
            if(rate==null){
                return new ResponseEntity(searchService.serviceSearch(keyword.trim()), HttpStatus.OK);

            } else {
                return new ResponseEntity(searchService.serviceSearch(keyword.trim(), rate), HttpStatus.OK);
    }
}
    @GetMapping("/service/location-coverage")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API accept skill and  location coverage and return the services")
    public ResponseEntity<Page<Services>> getServiceLocationCoverage(@Parameter(description = "location-coverage")  @RequestParam("location-coverage") String locationCoverage,
                                                                     @RequestParam("skill") String skill,
                                                                    @PageableDefault Pageable pageable){
        return new ResponseEntity(searchService.getListOfServicesByLocationCoverage(skill, locationCoverage, pageable), HttpStatus.OK);
    }
}
