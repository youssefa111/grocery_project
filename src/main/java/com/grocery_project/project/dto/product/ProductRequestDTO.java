package com.grocery_project.project.dto.product;

import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.File;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private File image;
    @NotNull
    @Valid
    private CategoryUpdateDTO categoryUpdateDTO;
    @NotNull
    @Valid
    private QuantityRequestDTO quantityRequestDTO;
}
