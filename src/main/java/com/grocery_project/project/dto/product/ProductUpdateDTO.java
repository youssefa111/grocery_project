package com.grocery_project.project.dto.product;

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
    private MultipartFile image;
}
