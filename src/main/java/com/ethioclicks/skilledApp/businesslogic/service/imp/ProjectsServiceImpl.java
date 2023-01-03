package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.ProjectImages;
import com.ethioclicks.skilledApp.businesslogic.entity.Projects;
import com.ethioclicks.skilledApp.businesslogic.entity.Services;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.repo.ProjectImageRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ProjectsRepo;
import com.ethioclicks.skilledApp.businesslogic.repo.ServicesRepo;
import com.ethioclicks.skilledApp.businesslogic.service.ProjectsService;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    private final ProjectsRepo projectsRepo;
    private final ServicesRepo servicesRepo;
    private final UserRegistrationService userRegistrationService;
    private final ProjectImageRepo projectImageRepo;


    public ProjectsServiceImpl(ProjectsRepo projectsRepo, ServicesRepo servicesRepo, UserRegistrationService userRegistrationService, ProjectImageRepo projectImageRepo) {
        this.projectsRepo = projectsRepo;
        this.servicesRepo = servicesRepo;

        this.userRegistrationService = userRegistrationService;
        this.projectImageRepo = projectImageRepo;
    }
    @Transactional
    @Override
    public List<Projects> saveProjects(Projects projects, Long serviceId) {
        Services existingService = servicesRepo.findById(serviceId).orElseThrow(()->new BadRequestException("Service Id not found"));
        if ( projects != null)
        {
            if(projects.getId()!=null){
                Projects existingProject = projectsRepo.findById(projects.getId()).orElseThrow(()-> new BadRequestException("Project Id not found"));
                existingProject.setProjectImages(projects.getProjectImages());
                existingProject.setProjectsGivenTo(projects.getProjectsGivenTo());
                existingProject.setDescription(projects.getDescription());
                existingProject.setTitle(projects.getTitle());
                existingProject.setTotalCost(projects.getTotalCost());
                existingProject.setTimeTaken(projects.getTimeTaken());
               existingService.getProjects().add(existingProject);
            }else{

                existingService.getProjects().add(projects);
            }
            servicesRepo.save(existingService);
            return existingService.getProjects();


        }
        else throw new BadRequestException("Project Model is required");
    }
    @Override
    public boolean isServiceOwner(Long serviceId, String pid) {
        User user = userRegistrationService.getUser(pid);
        Optional<Services> services = servicesRepo.findById(serviceId);
        if (user!=null && services.isPresent() && user.getUserPublicId().equals(services.get().getUser().getUserPublicId()) ) {
            return  true;
        }
        return false;
    }
    @Transactional
    @Override
    public void deleteProject(Long projectId) {

        Projects projects = projectsRepo.findById(projectId).orElseThrow(() -> new BadRequestException("Project with id not found"));
        if (projects != null && projects.getProjectImages() != null && !projects.getProjectImages().isEmpty()){
            for(ProjectImages projectImages : projects.getProjectImages()){
                projectImageRepo.deleteById(projectImages.getId());
            }
        }
        projectsRepo.deleteById(projectId);
        }
}
