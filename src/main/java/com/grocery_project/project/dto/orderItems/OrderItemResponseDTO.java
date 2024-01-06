package com.grocery_project.project.dto.orderItems;

import com.grocery_project.core.utils.CryptoUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Long orderId;
    private String productId;
    private Integer quantity;
    private BigDecimal pricePerUnit;

    public void setProductId(Long id) {
        this.productId = CryptoUtils.encrypt(id);

    }


}
