package com.revature.registry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
class TagServiceTest {
    @MockBean
    TagRepository tagRepository;

    @Autowired
    TagService tagService;

    // Static Variables
    static Tag tag1;
    static Tag tag2;

    @BeforeEach
    void setUp() {
        List<Tag> tagList = new LinkedList<>();
        tag1 = new Tag();
        tag1.setId(1);
        tag1.setName("Java");
        tag2 = new Tag();
        tag2.setId(2);
        tag2.setName("Angular");
        tagList.add(tag1);

        // Mock Repo
        when(tagRepository.findAll()).thenReturn(tagList);
        when(tagRepository.findById(tag1.getId())).thenReturn(Optional.ofNullable(tag1));
        when(tagRepository.save(tag2)).thenReturn(tag2);

    }

    @Test
    void testGetTagListNotEmpty() {
        assertThat(tagService.getAllTags()).isNotEmpty();
    }

    @Test
    void testGetTagByIdReturnTag() {
        assertThat(tagService.getTagById(tag1.getId())).isEqualTo(tag1);
    }

    @Test
    void testGetTagByIdReturnNull() {
        assertThat(tagService.getTagById(9999)).isNull();
    }

    @Test
    void testCreateTag() {
        /**
         * If you create a tag with a same name that's already in the database,
         * the same ID should be used.
         */
        Tag existingTagInDB = new Tag();
        existingTagInDB.setId(99);
        existingTagInDB.setName("TypeScript");
        Tag newTagTryingToOverideDB = new Tag();
        newTagTryingToOverideDB.setName(existingTagInDB.getName());

        when(tagRepository.findByName(newTagTryingToOverideDB.getName()))
                .thenReturn(Optional.ofNullable(existingTagInDB));
        when(tagRepository.save(any())).thenReturn(newTagTryingToOverideDB);

        assertEquals(existingTagInDB.getId(), tagService.createTag(newTagTryingToOverideDB).getId());
    }

    @Test
    void testEnableTag() {
        assertEquals(tag1, tagService.enableTag(tag1.getId()));
    }

    @Test
    void testDisableTag() {
        assertEquals(tag1, tagService.disableTag(tag1.getId()));
    }
}
