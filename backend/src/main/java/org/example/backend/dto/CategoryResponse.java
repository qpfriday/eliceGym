package org.example.backend.dto;
import lombok.Getter;
import org.example.backend.entity.Category;

@Getter
public class CategoryResponse {

    private final String name;
    private final String description;
    private final String createdAt;
    private final String updatedAt;

    public CategoryResponse(Category category){
        this.name = category.getName();
        this.description = category.getDescription();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = getUpdatedAt();
    }
}