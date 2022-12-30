package com.ethioclicks.skilledApp.businesslogic.controller;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import com.ethioclicks.skilledApp.businesslogic.service.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/projects")
public class ProjectController {

    private final ProjectsService projectsService;

    public ProjectController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping("/projects/saveProjects/{serviceId}")
    @Operation(description = "This API receive Project service Information and Create Project detail ")
    public ResponseEntity saveProjects  (@Parameter(description = "Project's Information" )@RequestBody Projects projectsModel,
                                        @PathVariable(name = "serviceId") Long serviceId)throws Exception{

        List<Projects> savedProjects = projectsService.saveProjects(projectsModel, serviceId);
        return new ResponseEntity(savedProjects, HttpStatus.OK);
    }
}
