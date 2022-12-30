package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.ProjectsModel;
import com.ethioclicks.skilledApp.businesslogic.repo.ProjectImageRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ProjectsRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ProjectsService;
import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    private final ProjectsRepo projectsRepo;
    private final ServicesRepo servicesRepo;


    public ProjectsServiceImpl(ProjectsRepo projectsRepo, ServicesRepo servicesRepo) {
        this.projectsRepo = projectsRepo;
        this.servicesRepo = servicesRepo;

    }
    @Override
    public List<Projects> saveProjects(Projects projectsModel, Long serviceId) {
        if (projectsModel != null)
        {
            Optional<Services> services = servicesRepo.findById(serviceId);

            List<Projects> projects = (Arrays.asList(projectsModel)) ;
            List<Projects>projectsForService = new ArrayList<>(projects);
            services.get().setProjects(projectsForService);
            servicesRepo.save(services.get());
            return services.get().getProjects();
        }
        else throw new BadRequestException("Project Model is required");
    }
}
