package com.grocery_project.project.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponseDTO {

    private Long id;

    private Integer discPercent;

    private Boolean isActive;

    private LocalDate expireDate;

}
