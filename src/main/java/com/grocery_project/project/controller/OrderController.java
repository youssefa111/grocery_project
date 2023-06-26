package com.grocery_project.project.controller;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.constant.AppConstants;
import com.grocery_project.project.dto.order.CreateOrderRequestDTO;
import com.grocery_project.project.dto.order.CreateOrderResponseDTO;
import com.grocery_project.project.dto.order.OrderRequestDto;
import com.grocery_project.project.dto.order.OrderResponseDTO;
import com.grocery_project.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(AppConstants.USER)
    public BaseResponse<CreateOrderResponseDTO> save(@RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        return orderService.save(createOrderRequestDTO);
    }
}
