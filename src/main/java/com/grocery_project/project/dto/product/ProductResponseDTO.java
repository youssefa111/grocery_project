package com.grocery_project.project.dto.product;

import com.grocery_project.project.dto.category.CategoryResponseDTO;
import com.grocery_project.project.dto.discount.DiscountResponseDTO;
import com.grocery_project.project.dto.quantity.QuantityResponseDTO;

import lombok.*;

import java.io.File;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private File image;

    private CategoryResponseDTO categoryResponseDTO;

    private QuantityResponseDTO quantityResponseDTO;

    private DiscountResponseDTO discountResponseDTO;
}
