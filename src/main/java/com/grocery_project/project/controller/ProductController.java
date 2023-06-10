package com.grocery_project.project.controller;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.product.ProductRequestDTO;
import com.grocery_project.project.dto.product.ProductResponseDTO;
import com.grocery_project.project.dto.product.ProductUpdateDTO;
import com.grocery_project.project.entity.Product;
import com.grocery_project.project.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("{baseUrl}/product")
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public BaseResponse<String> save(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        return productService.save(productRequestDTO);
    }

    @PutMapping
    public BaseResponse<Product> update(@Valid @RequestBody ProductUpdateDTO productUpdateDTO){
        return productService.update(productUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete (@Valid @NotNull @PathVariable("id")Long id){
        return productService.delete(id);
    }

    @GetMapping
    public ResponseEntity< BaseResponse<List<ProductResponseDTO>>> findAll(){
        BaseResponse<List<ProductResponseDTO>> result = productService.findAll();
        return ResponseEntity.ok(result);
    }
}
