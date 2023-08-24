package com.grocery_project.project.dto.product;

import com.grocery_project.core.utils.CryptoUtils;
import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.discount.DiscountResponseDTO;
import com.grocery_project.project.dto.quantity.QuantityResponseDTO;

import lombok.*;

import java.io.File;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isStocked;

    private Boolean status;

    private String imageUrl;

    private Long purchaseNum;

    private CategoryResponseDTO categoryResponseDTO;

    private QuantityResponseDTO quantityResponseDTO;

    private DiscountResponseDTO discountResponseDTO;

    private Instant createdAt;

    private Instant updatedAt;

    public void setId(Long id) {
        this.id = CryptoUtils.encrypt(id);;
    }
}
