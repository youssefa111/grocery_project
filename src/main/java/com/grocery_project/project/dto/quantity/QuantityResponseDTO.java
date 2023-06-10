package com.grocery_project.project.dto.quantity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityResponseDTO {

    private Long id;
    private Long itemQnt;
    private Long minQnt;
}
