package com.grocery_project.project.controller;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.constant.AppConstants;
import com.grocery_project.project.dto.category.CategoryRequestDTO;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
import com.grocery_project.project.entity.Category;
import com.grocery_project.project.entity.Quantity;
import com.grocery_project.project.service.QuantityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/quantity")
@Secured(AppConstants.ADMIN)
@Validated
public class QuantityController {

    private final QuantityService quantityService;


    @PostMapping
    public BaseResponse<Quantity> save(@Valid @RequestBody QuantityRequestDTO quantityRequestDTO) {
        return quantityService.save(quantityRequestDTO);
    }

    @PutMapping
    public BaseResponse<Quantity> update(@Valid @RequestBody QuantityUpdateDTO quantityUpdateDTO) {
        return quantityService.update(quantityUpdateDTO);
    }


}
