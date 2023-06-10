package com.grocery_project.project.dto.discount;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountUpdateDTO extends  DiscountRequestDTO{

    @NotNull
    private Long id;
    @NotNull
    private Boolean isActive;
}
