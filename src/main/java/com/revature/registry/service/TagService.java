package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tRepo;

    public TagService() {

    }

    @Autowired
    public TagService(TagRepository tRepo) {
        this.tRepo = tRepo;
    }

    public List<Tag> getAllTags() {
        return tRepo.findAll();
    }

    public Tag getTagById(int id) {
        return tRepo.findById(id).orElse(null);
    }

    public Tag createTag(Tag tag) {
        /**
         * If you create a tag with a same name that's already in the database,
         * the same ID should be used.
         */
        Optional<Tag> optionalTag = tRepo.findByName(tag.getName());
        if (optionalTag.isPresent()) {
            tag.setId(optionalTag.get().getId());
        }
        return tRepo.save(tag);
    }

    public Tag enableTag(int id) {
        tRepo.enableTag(id);
        return tRepo.findById(id).orElse(null);
    }

    public Tag disableTag(int id) {
        tRepo.disableTag(id);
        return tRepo.findById(id).orElse(null);
    }
}
