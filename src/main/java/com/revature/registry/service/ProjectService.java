package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectRepository pRepo;

    public ProjectService() {

    }

    @Autowired
    public ProjectService(ProjectRepository pRepo) {
        this.pRepo = pRepo;
    }

    public List<Project> getAllProjects() {
        return pRepo.findAll();
    }

    public Project getProjectById(int id) {
        return pRepo.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        Project savedProject = pRepo.save(project);
        return savedProject;
    }

    public Project updateProjectById(int id, Project newProject) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            newProject.setId(id);
            pRepo.save(newProject);
            return project.get();
        }
        return null;
    }

    public Boolean deleteProjectById(int id) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            pRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
