package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.Reviews;
import com.ethioclicks.skilledApp.businesslogic.model.ReviewsModel;
import com.ethioclicks.skilledApp.businesslogic.service.ReviewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class ReviewController {

    private final ReviewsService reviewsService;

    public ReviewController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping("review/{serviceId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This api accepts review and return review info")
    ResponseEntity  CreateReview(@Parameter(description = "serviceId and Reviews")
                                 @RequestBody ReviewsModel reviewsModel, @PathVariable(name = "serviceId")Long serviceId,
                                 @RequestHeader(value = "pid") String pid){
        reviewsService.giveReview(reviewsModel, serviceId, pid);

        return new ResponseEntity("", HttpStatus.OK);
    }


}
