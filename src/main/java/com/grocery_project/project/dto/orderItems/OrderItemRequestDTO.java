package com.grocery_project.project.dto.orderItems;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
public class OrderItemRequestDTO {

    @JsonIgnore
    private  Long orderId;
    @NotNull
    private  Long productId;
    @NotNull
    private  Integer quantity;
    @JsonIgnore
    private  BigDecimal pricePerUnit;
}
