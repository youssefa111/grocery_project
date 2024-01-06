package com.grocery_project.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery_project.core.annotations.auth.AllRole;
import com.grocery_project.core.annotations.auth.IsAdmin;
import com.grocery_project.core.base.BaseResponse;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/product")
@Validated
@IsAdmin
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<BaseResponse<Product>> save(@Valid @RequestBody ProductRequestDTO productRequestDTO, @RequestParam("image") MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRequestDTO, image));
    }

    @PostMapping("/test")
    public ResponseEntity<BaseResponse<Product>> saveTest(String jsonFileVo, @RequestParam("image") MultipartFile image) throws IOException {

        ProductRequestDTO productRequestDTO = new ObjectMapper().readValue(jsonFileVo, ProductRequestDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRequestDTO, image));
    }

    @PutMapping
    public BaseResponse<Product> update(@Valid @RequestBody ProductUpdateDTO productUpdateDTO, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        return productService.update(productUpdateDTO, image);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@Valid @NotNull @PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductResponseDTO>>> findAll() {
        BaseResponse<List<ProductResponseDTO>> result = productService.findAll();
        return ResponseEntity.ok(result);
    }

    @AllRole
    @GetMapping("/active")
    public ResponseEntity<BaseResponse<List<ProductResponseDTO>>> findAllActives() {
        BaseResponse<List<ProductResponseDTO>> result = productService.findActiveProducts();
        return ResponseEntity.ok(result);
    }
}
