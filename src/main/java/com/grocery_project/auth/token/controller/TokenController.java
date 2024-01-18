package com.grocery_project.auth.token.controller;

import com.grocery_project.auth.token.service.TokenService;
import com.grocery_project.core.annotations.auth.AllRole;
import com.grocery_project.core.base.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/token")
@Validated
@AllRole
public class TokenController {

    public TokenService tokenService;

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse<?>> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var result = tokenService.refreshToken(request, response);
        return result == null ?
                ResponseEntity.badRequest().body(new BaseResponse<>("There Something error is Happend", "ERROR", HttpStatus.BAD_REQUEST.value()))
                : ResponseEntity.ok(result);

    }

}
