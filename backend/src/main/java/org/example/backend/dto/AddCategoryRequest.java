package org.example.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.entity.Category;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCategoryRequest {

    private String name;
    private String description;

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .desc(description)
                .build();
    }
}