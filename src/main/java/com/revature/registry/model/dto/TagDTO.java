package com.revature.registry.model.dto;

import lombok.Data;

@Data
public class TagDTO {
    private String name;

    private String description;

    private Boolean isEnabled;
}
