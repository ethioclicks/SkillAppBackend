package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import com.ethioclicks.skilledApp.businesslogic.service.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectsService projectsService;

    public ProjectController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping("/projects/saveProjects/")
    @Operation(description = "This API receive Project service Information and Create Project detail ")
    public ResponseEntity saveProjects (@Parameter(description = "Project's Information" )@RequestBody Projects projectsModel,
                                         @Parameter(name = "serviceId") Long serviceId,
                                         @RequestHeader(name = "pid")String pid){

        if(projectsService.isServiceOwner(serviceId,pid)){
            List<Projects> savedProjects = projectsService.saveProjects(projectsModel, serviceId);
            return new ResponseEntity(savedProjects, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("You did not have a permission",HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping( "projects/{projectId}" )
    @SecurityRequirement( name = "bearerAuth" )
    @Operation( description = "This API accept service Id and delete the service" )
    public ResponseEntity deleteProjects(@Parameter( description = "project Id") @PathVariable( "projectId" ) Long projectId) {

        projectsService.deleteProject(projectId);
        return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
    }
}
