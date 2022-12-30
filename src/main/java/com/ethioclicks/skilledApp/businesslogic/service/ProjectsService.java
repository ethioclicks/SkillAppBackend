package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import com.ethioclicks.skilledApp.businesslogic.model.ServicesModel;

import java.util.List;

public interface ProjectsService  {
   List<Projects> saveProjects(Projects projectsModel, Long serviceId);
}
