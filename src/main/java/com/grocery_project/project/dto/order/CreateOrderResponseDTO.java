package com.grocery_project.project.dto.order;

import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderResponseDTO {
    private final OrderResponseDTO orderResponseDTO;
    private final List<OrderItemResponseDTO> orderItemResponseDTOList;
}
