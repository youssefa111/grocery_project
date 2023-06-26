package com.grocery_project.project.dto.order;

import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDTO {

    @NotNull
    @Valid
    private final OrderRequestDto orderRequestDto;

    @NotEmpty
    @Valid
    private final List<OrderItemRequestDTO> orderItemRequestDTO;
}
