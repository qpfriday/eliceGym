package org.example.backend.controller;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AddCategoryRequest;
import org.example.backend.dto.CategoryResponse;
import org.example.backend.dto.UpdateCategoryRequest;
import org.example.backend.entity.Category;
import org.example.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/categories")
    public ResponseEntity<Category> addCategory(@RequestBody AddCategoryRequest request){
        Category savedCategory = categoryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCategory);
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryResponse>> findAllCategories(){
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(categories);
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<CategoryResponse> findCategory(@PathVariable int id){
        Category category = categoryService.findById(id);

        return ResponseEntity.ok()
                .body(new CategoryResponse(category));
    }
    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id){
        categoryService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest request){
        Category updateCategory = categoryService.update(id, request);

        return ResponseEntity.ok()
                .body(updateCategory);
    }
}