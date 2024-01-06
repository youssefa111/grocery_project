package com.grocery_project.project.dto.order;

import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponseDTO {
    private OrderResponseDTO orderResponseDTO;
    private List<OrderItemResponseDTO> orderItemResponseDTOList;
}
