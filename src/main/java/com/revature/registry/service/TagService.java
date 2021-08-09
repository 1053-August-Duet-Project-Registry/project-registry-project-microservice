package com.revature.registry.service;

import java.util.List;

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

    public Boolean createTag(Tag tag) {
        Tag savedTag = tRepo.save(tag);
        return savedTag.getId() != 0;
    }
}
