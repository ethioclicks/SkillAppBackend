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
        Optional<Services> services = servicesRepo.findById(serviceId);

        if (projects != null)

        {
            if(projects.getId()!=null){
                Optional<Projects> existingProject = projectsRepo.findById(projects.getId());
                existingProject.get().setProjectImages(projects.getProjectImages());
                existingProject.get().setProjectsGivenTo(projects.getProjectsGivenTo());
                existingProject.get().setDescription(projects.getDescription());
                existingProject.get().setTitle(projects.getTitle());
                existingProject.get().setTotalCost(projects.getTotalCost());
                existingProject.get().setTimeTaken(projects.getTimeTaken());
                List<Projects> projectsToUpdate = (Arrays.asList(existingProject.get())) ;
                List<Projects>projectsForService = new ArrayList<>(projectsToUpdate);
                services.get().setProjects(projectsForService);
            }else{

                List<Projects> newProjects = (Arrays.asList(projects)) ;
                List<Projects>projectsForService = new ArrayList<>(newProjects);
                services.get().setProjects(projectsForService);

            }
            servicesRepo.save(services.get());
            return services.get().getProjects();


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
    public void deleteProject(Long projectId, String pid) {

        Projects projects = projectsRepo.findById(projectId).orElseThrow(() -> new BadRequestException("Project with id not found"));
        if (projects != null && projects.getProjectImages() != null && !projects.getProjectImages().isEmpty()){
            for(ProjectImages projectImages : projects.getProjectImages()){
                projectImageRepo.deleteById(projectImages.getId());
            }
        }
        projectsRepo.deleteById(projectId);
        }
}
