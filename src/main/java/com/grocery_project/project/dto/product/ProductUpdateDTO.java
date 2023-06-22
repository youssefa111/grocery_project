package com.grocery_project.project.dto.product;

import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {
    @NotNull
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long purchaseNum;
    private Boolean isStocked;
    private Boolean status;
    private CategoryUpdateDTO categoryUpdateDTO;
    private QuantityUpdateDTO quantityUpdateDTO;
}
