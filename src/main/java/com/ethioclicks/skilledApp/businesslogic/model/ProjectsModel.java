package com.ethioclicks.skilledApp.businesslogic.model;

import com.ethioclicks.skilledApp.businesslogic.entity.ProjectImages;
import lombok.Data;

import java.util.List;
@Data
public class ProjectsModel {
    private Long id;
    private String title;
    private List<ProjectImages> projectImages;
    private String description;
    private Double totalCost;
    private String projectsGivenTo;
    private Integer timeTaken;


}
