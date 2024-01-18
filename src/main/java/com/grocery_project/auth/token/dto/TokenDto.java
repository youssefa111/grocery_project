package com.grocery_project.auth.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {

    private String accessToken;
    private String refreshToken;
    private Date expireDate;
}
