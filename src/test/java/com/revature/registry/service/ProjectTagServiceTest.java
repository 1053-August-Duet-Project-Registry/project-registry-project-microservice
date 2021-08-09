package com.revature.registry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Project;
import com.revature.registry.model.Tag;
import com.revature.registry.repository.ProjectRepository;
import com.revature.registry.repository.TagRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
class ProjectTagServiceTest {
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    TagRepository tagRepository;

    @Autowired
    ProjectTagService projectTagService;

    static Project project1;

    @BeforeEach
    void setUp() {
        project1 = new Project();
        project1.setId(10);
        Tag tag0 = new Tag();
        tag0.setName("tag0");
        project1.getTags().add(tag0);

        Tag tag1 = new Tag();
        tag1.setName("tag1");
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        List<Tag> tags = new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag2);
            }
        };

        when(projectRepository.findById(project1.getId())).thenReturn(Optional.ofNullable(project1));
        when(projectRepository.save(project1)).thenReturn(project1);
        when(tagRepository.findAllById(Arrays.asList(1, 2))).thenReturn(tags);
    }

    @Test
    void testAddRemoveTagsToProject() {
        Project p;
        p = projectTagService.addTagsToProject(project1.getId(), Arrays.asList(1, 2));
        assertEquals(3, p.getTags().size());
        p = projectTagService.removeTagsFromProject(project1.getId(), Arrays.asList(1, 2));
        assertEquals(1, p.getTags().size());
    }
}
