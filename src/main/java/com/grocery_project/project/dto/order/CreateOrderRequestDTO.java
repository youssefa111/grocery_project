package com.grocery_project.project.dto.order;

import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @NotNull
    @Valid
    private OrderRequestDto orderRequestDto;

    @NotEmpty
    @Valid
    private List<OrderItemRequestDTO> orderItemRequestDTO;
}
