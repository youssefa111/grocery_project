package com.grocery_project.project.mapper;

import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO categoryRequestDTO);
    Category toEntity(CategoryUpdateDTO categoryUpdateDTO);
    CategoryResponseDTO toDTO(Category category);
}
