package com.grocery_project.project.dto.category;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDTO extends CategoryRequestDTO{

    @NotNull
    private Long id;
}
