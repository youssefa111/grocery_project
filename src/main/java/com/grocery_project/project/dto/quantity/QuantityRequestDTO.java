package com.grocery_project.project.dto.quantity;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityRequestDTO {

    @NotNull
    private Long itemQnt;
    @NotNull
    private Long minQnt;
}
