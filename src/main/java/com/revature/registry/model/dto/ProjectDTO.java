package com.revature.registry.model.dto;

import java.util.List;

import com.revature.registry.model.Account;
import com.revature.registry.model.Status;
import com.revature.registry.model.Tag;

import lombok.Data;

@Data
public class ProjectDTO {
    private String name;

    private Status status;

    private String description;

    private Account owner;

    private List<Tag> tags;
}
