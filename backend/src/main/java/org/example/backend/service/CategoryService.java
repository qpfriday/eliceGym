package org.example.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AddCategoryRequest;
import org.example.backend.dto.UpdateCategoryRequest;
import org.example.backend.entity.Category;
import org.example.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(AddCategoryRequest request){
        return categoryRepository.save(request.toEntity());
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(int id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Category update(int id, UpdateCategoryRequest request){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not fount: " + id));

        category.update(request.getName(), request.getDesc());

        return category;
    }
}
