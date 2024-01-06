package com.grocery_project.project.controller;

import com.grocery_project.core.annotations.auth.IsCustomer;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.order.CreateOrderRequestDTO;
import com.grocery_project.project.dto.order.CreateOrderResponseDTO;
import com.grocery_project.project.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/order")
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @IsCustomer
    public BaseResponse<CreateOrderResponseDTO> save(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        return orderService.save(createOrderRequestDTO);
    }
}
