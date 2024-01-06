package com.grocery_project.project.controller;

import com.grocery_project.core.annotations.auth.AllRole;
import com.grocery_project.core.annotations.auth.IsAdmin;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.entity.Category;
import com.grocery_project.project.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/category")
@Validated
@IsAdmin
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public BaseResponse<Category> save(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.save(categoryRequestDTO);
    }

    @PutMapping
    public BaseResponse<Category> update(@Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        return categoryService.update(categoryUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@Valid @NotNull @PathVariable("id") Long id) {
        return categoryService.delete(id);
    }

    @AllRole
    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponseDTO>>> findAll() {
        BaseResponse<List<CategoryResponseDTO>> result = categoryService.findAll();
        return ResponseEntity.ok(result);
    }
}
