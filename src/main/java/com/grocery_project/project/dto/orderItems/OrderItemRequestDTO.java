package com.grocery_project.project.dto.orderItems;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grocery_project.core.utils.CryptoUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDTO {

    @JsonIgnore
    private Long orderId;
    @NotNull
    private String productId;
    @NotNull
    private Integer quantity;
    @JsonIgnore
    private BigDecimal pricePerUnit;

    public Long getProductId() {
        return Long.parseLong(CryptoUtils.decrypt(productId));
    }
}
