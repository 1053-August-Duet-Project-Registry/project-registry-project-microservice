package com.revature.registry.controller;

import java.util.List;

import com.revature.registry.model.Project;
import com.revature.registry.service.ProjectTagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectTagController {
    @Autowired
    private ProjectTagService ptServ;

    @PutMapping("id/{id}/addTag")
    public ResponseEntity<Project> addTagsToProject(@PathVariable("id") int id, @RequestBody List<Integer> tagIDs) {
        Project updatedProject = ptServ.addTagsToProject(id, tagIDs);

        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @PutMapping("id/{id}/removeTag")
    public ResponseEntity<Project> removeTagsToProject(@PathVariable("id") int id, @RequestBody List<Integer> tagIDs) {
        Project updatedProject = ptServ.removeTagsFromProject(id, tagIDs);

        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}
