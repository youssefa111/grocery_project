package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.DuplicateRecordException;
import com.grocery_project.core.exception_handling.exception.RecordNotFoundException;
import com.grocery_project.core.utils.FirebaseService;
import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.product.ProductRequestDTO;
import com.grocery_project.project.dto.product.ProductResponseDTO;
import com.grocery_project.project.dto.product.ProductUpdateDTO;
import com.grocery_project.project.entity.Category;
import com.grocery_project.project.entity.Product;
import com.grocery_project.project.mapper.ProductMapper;
import com.grocery_project.project.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FirebaseService firebaseService;

    @Transactional
    public BaseResponse<Product> save(ProductRequestDTO categoryRequestDTO, MultipartFile image) throws IOException {
        isProductExist(categoryRequestDTO.getName());
        Product entity = productMapper.toEntity(categoryRequestDTO);
        var result = productRepository.save(entity);
        String imageUrl = firebaseService.uploadImage(image);
        result.setImageUrl(imageUrl);

        return  new BaseResponse<>(result,"This product is added Successfully!");
    }

    private void isProductExist(String name){
        productRepository.findByName(name.toLowerCase())
                .ifPresent(product1 -> {
                    throw new DuplicateRecordException("This Product: "+ name
                            +" already exists,You cant add it again");
                });

    }


    @Transactional
    public BaseResponse<Product> update(ProductUpdateDTO productUpdateDTO) {
        Product entity = productMapper.toEntity(productUpdateDTO);
      var result =  productRepository.save(entity);
        return  new BaseResponse<>(result,"This product is updated Successfully!");
    }

    @Transactional
    public BaseResponse<String> delete(Long id) {
        productRepository.deleteById(id);
        return  new BaseResponse<>(null,"This Product is deleted Successfully!");
    }

    public BaseResponse<ProductResponseDTO> findById(Long id){
        Optional<Product> entity =  productRepository.findById(id);
        if (entity.isPresent()){
            return  new BaseResponse<>(productMapper.toDTO(entity.get()));
        }
        else
        {
            throw new RecordNotFoundException("This category with id:- {"+ id + "} not found");
        }
    }


    public BaseResponse<List<ProductResponseDTO>> findAll() {

        try {
            var entities = productRepository.findAll();
            List<ProductResponseDTO> result = new ArrayList<>();
            entities.forEach(product -> result.add(productMapper.toDTO(product)));
            return new BaseResponse<>(result);

        }catch (Exception e){

            e.printStackTrace();
        }
        return null;
    }
}
