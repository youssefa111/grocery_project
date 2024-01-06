package com.grocery_project.project.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import jakarta.validation.Valid;
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
public class ProductRequestDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;

    @JsonIgnore
    private Long purchaseNum = 0L;

    @JsonIgnore
    private Boolean isStocked = false;

    @JsonIgnore
    private Boolean status = true;
    //    @NotNull
//    private MultipartFile image;
    @NotNull
    @Valid
    private CategoryUpdateDTO categoryUpdateDTO;
    @NotNull
    @Valid
    private QuantityRequestDTO quantityRequestDTO;
}
