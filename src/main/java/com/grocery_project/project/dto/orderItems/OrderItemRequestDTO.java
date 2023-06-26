package com.grocery_project.project.dto.orderItems;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequestDTO {

    @JsonIgnore
    private final Long orderId;
    @NotNull
    private final Long productId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final BigDecimal pricePerUnit;
}
