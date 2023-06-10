package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.DuplicateRecordException;
import com.grocery_project.core.exception_handling.exception.RecordNotFoundException;
import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.entity.Category;
import com.grocery_project.project.mapper.CategoryMapper;
import com.grocery_project.project.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public BaseResponse<Category> save(CategoryRequestDTO categoryRequestDTO) {
        isCategoryExist(categoryRequestDTO.getCategory());
        Category entity = categoryMapper.toEntity(categoryRequestDTO);
      var result =  categoryRepository.save(entity);
        return  new BaseResponse<>(result,"This category is added Successfully!");
    }

    private void isCategoryExist(String category){
        categoryRepository.findByCategory(category)
                .ifPresent(category1 -> {
                    throw new DuplicateRecordException("This Category: "+ category
                            +" already exists,You cant add it again");
                });

    }

    @Transactional
    public BaseResponse<Category> update(CategoryUpdateDTO categoryUpdateDTO) {
        Category entity = categoryMapper.toEntity(categoryUpdateDTO);
        var result =categoryRepository.save(entity);
        return  new BaseResponse<>(result,"This category is updated Successfully!");
    }

    @Transactional
    public BaseResponse<String> delete(Long id) {
        categoryRepository.deleteById(id);
        return  new BaseResponse<>(null,"This category is deleted Successfully!");
    }

    public BaseResponse<CategoryResponseDTO> findById(Long id){
        Optional<Category> entity =  categoryRepository.findById(id);
        if (entity.isPresent()){
            return  new BaseResponse<>(categoryMapper.toDTO(entity.get()));
        }
        else
        {
        throw new RecordNotFoundException("This category with id:- {"+ id + "} not found");
        }
    }

    public BaseResponse<List<CategoryResponseDTO>> findAll() {
        var entities = categoryRepository.findAll();
        List<CategoryResponseDTO> result = new ArrayList<>();
        entities.forEach(category -> result.add(categoryMapper.toDTO(category)));
        return new BaseResponse<>(result);
    }
}
