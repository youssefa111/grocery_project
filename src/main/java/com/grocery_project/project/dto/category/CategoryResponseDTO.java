package com.grocery_project.project.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4439114469417994311L;
    private Long id;
    private String category;
}
