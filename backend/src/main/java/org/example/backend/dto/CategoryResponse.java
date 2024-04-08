package org.example.backend.dto;

import lombok.Getter;
import org.example.backend.entity.Category;

@Getter
public class CategoryResponse {

    private final String name;
    private final String desc;

    public CategoryResponse(Category category){
        this.name = category.getName();
        this.desc = category.getDesc();
    }
}
