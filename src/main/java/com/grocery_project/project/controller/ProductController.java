package com.grocery_project.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.constant.AppConstants;
import com.grocery_project.core.utils.FirebaseService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/product")
@Validated
@Secured(AppConstants.ADMIN)
public class ProductController {

    private final ProductService productService;
    private final FirebaseService firebaseService;

    @PostMapping
    public ResponseEntity<BaseResponse<Product>> save(@Valid @RequestBody ProductRequestDTO productRequestDTO , @RequestParam("image")MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRequestDTO,image));
    }

    @PostMapping("/profile/pic")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        return firebaseService.upload(multipartFile);
    }


    @PostMapping("/test")
    public ResponseEntity<BaseResponse<Product>> saveTest( String jsonFileVo , @RequestParam("image")MultipartFile image) throws IOException {

        ProductRequestDTO productRequestDTO = new ObjectMapper().readValue(jsonFileVo, ProductRequestDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRequestDTO,image));
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
