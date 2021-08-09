package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository pRepo;

    public List<Project> getAllProjects() {
        return pRepo.findAll();
    }

    public Project getProjectById(int id) {
        return pRepo.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        return pRepo.save(project);
    }

    public Project updateProjectById(int id, Project newProject) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            newProject.setId(id);
            return pRepo.save(newProject);
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
