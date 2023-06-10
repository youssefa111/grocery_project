package com.grocery_project.project.dto.quantity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityUpdateDTO extends QuantityRequestDTO{

    @NotNull
    private Long id;
}
