package com.grocery_project.project.dto.product;

import com.grocery_project.core.utils.CryptoUtils;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
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
public class ProductUpdateDTO {
    @NotNull
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long purchaseNum;
    private Boolean isStocked;
    private Boolean status;
    private CategoryUpdateDTO categoryUpdateDTO;
    private QuantityUpdateDTO quantityUpdateDTO;

    public Long getId() {
        return Long.parseLong(CryptoUtils.decrypt(id));
    }

}
