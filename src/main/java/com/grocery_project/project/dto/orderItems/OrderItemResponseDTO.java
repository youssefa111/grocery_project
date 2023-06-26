package com.grocery_project.project.dto.orderItems;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
    private final Long orderId;
    private final Long productId;
    private final Integer quantity;
    private final BigDecimal pricePerUnit;
}
