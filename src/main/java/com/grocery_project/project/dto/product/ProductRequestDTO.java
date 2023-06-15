package com.grocery_project.project.dto.product;

import com.grocery_project.core.listeners.LowercaseEntityListener;
import com.grocery_project.project.dto.category.CategoryUpdateDTO;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import jakarta.persistence.EntityListeners;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
//    @NotNull
//    private MultipartFile image;
    @NotNull
    @Valid
    private CategoryUpdateDTO categoryUpdateDTO;
    @NotNull
    @Valid
    private QuantityRequestDTO quantityRequestDTO;
}
