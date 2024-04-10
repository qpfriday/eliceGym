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
    private String desc;

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .desc(desc)
                .build();
    }
}