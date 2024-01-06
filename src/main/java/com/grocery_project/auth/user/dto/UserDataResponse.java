package com.grocery_project.auth.user.dto;

import com.grocery_project.auth.token.dto.TokenDto;
import com.grocery_project.core.utils.CryptoUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse extends RegisterDto {

    private String id;
    private TokenDto token;

    public void setId(Long id) {
        this.id = CryptoUtils.encrypt(id);
        ;
    }
}