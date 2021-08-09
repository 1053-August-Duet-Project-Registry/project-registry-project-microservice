package com.revature.registry.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Project;
import com.revature.registry.service.ProjectTagService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
public class ProjectTagControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ProjectTagService projectTagService;

    @Autowired
    @InjectMocks
    private ProjectTagController projectTagController;

    // static variables
    Project p1;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectTagController).build();
        p1 = new Project();
        p1.setId(101);
    }

    @Test
    public void testAddRemoveTagsToProject() throws Exception {
        List<Integer> tagIDs = Arrays.asList(1, 2);
        when(projectTagService.addTagsToProject(p1.getId(), tagIDs)).thenReturn(p1);
        when(projectTagService.removeTagsFromProject(p1.getId(), tagIDs)).thenReturn(p1);

        mockMvc.perform(put("/api/project/id/" + p1.getId() + "/addTag").contentType(MediaType.APPLICATION_JSON)
                .content(tagIDs.toString())).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(p1.getId()));

        mockMvc.perform(put("/api/project/id/" + p1.getId() + "/removeTag").contentType(MediaType.APPLICATION_JSON)
                .content(tagIDs.toString())).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(p1.getId()));
    }

}
