package com.grocery_project.project.controller;


import com.grocery_project.core.annotations.auth.IsAdmin;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.discount.DiscountRequestDTO;
import com.grocery_project.project.dto.discount.DiscountUpdateDTO;
import com.grocery_project.project.entity.Discount;
import com.grocery_project.project.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/discount")
@Validated
@IsAdmin
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<BaseResponse<Discount>> save(@Valid @RequestBody DiscountRequestDTO discountRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.save(discountRequestDTO));
    }

    @PutMapping
    public BaseResponse<Discount> update(@Valid @RequestBody DiscountUpdateDTO discountUpdateDTO) {
        return discountService.update(discountUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") Long id) {
        return discountService.delete(id);
    }

    @PostMapping("/deactivate/{id}")
    public BaseResponse<Discount> deactivate(@PathVariable("id") Long id) {
        return discountService.deactivate(id);
    }
}
