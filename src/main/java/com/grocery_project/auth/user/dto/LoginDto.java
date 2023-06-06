package com.grocery_project.auth.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    @Size(min = 3 , max = 30)
    private String username;

    @NotBlank
    @Size(min = 7 , max = 30)
    private String password;
}
