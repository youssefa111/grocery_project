package com.grocery_project.auth.user.controller;


import com.grocery_project.auth.user.dto.LoginDto;
import com.grocery_project.auth.user.dto.RegisterDto;
import com.grocery_project.auth.user.service.UserService;
import com.grocery_project.core.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseUrl}/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService service;

    @PostMapping("/auth/register")
    @Operation(
            description = "Register an account",
            responses = {
                    @ApiResponse(responseCode ="400", ref = "badRequestAPI"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorAPI"),
                    @ApiResponse(responseCode = "404", ref="recordNotFoundExceptionAPI"),
                    @ApiResponse(responseCode = "409", ref="duplicateRecordExceptionAPI"),
                    @ApiResponse(
                            responseCode = "201",
                            description = "User Account Created Successfully!",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = UserRequest.registerRequest
                                            )
                                    }
                            )

                    )
            }
    )
    public ResponseEntity<BaseResponse<String>> register(@RequestBody  @Valid RegisterDto request){

        return  ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RegisterDto> login(@RequestBody @Valid LoginDto request){

        return  ResponseEntity.ok(service.signin(request));
    }
}
