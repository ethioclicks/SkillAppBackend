package com.ethioclicks.skilledApp.businesslogic.service;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;


import java.util.List;


public interface ProjectsService  {
   List<Projects> saveProjects(Projects projectsModel, Long serviceId);
    boolean isServiceOwner(Long serviceId, String pid);

    void deleteProject( Long projectId, String pid);

}
