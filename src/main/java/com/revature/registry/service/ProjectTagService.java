package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Project;
import com.revature.registry.model.Tag;
import com.revature.registry.repository.ProjectRepository;
import com.revature.registry.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTagService {
    @Autowired
    private ProjectRepository pRepo;
    @Autowired
    private TagRepository tRepo;

    public Project addTagsToProject(int id, List<Integer> tagIDs) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            List<Tag> tags = tRepo.findAllById(tagIDs);
            Project p = project.get();
            p.getTags().addAll(tags);
            return pRepo.save(p);
        }
        return null;
    }

    public Project removeTagsFromProject(int id, List<Integer> tagIDs) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            List<Tag> tags = tRepo.findAllById(tagIDs);
            Project p = project.get();
            p.getTags().removeAll(tags);
            return pRepo.save(p);
        }
        return null;
    }
}
