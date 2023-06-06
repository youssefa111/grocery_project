package com.grocery_project.auth.user.service;

import com.grocery_project.auth.token.service.TokenService;
import com.grocery_project.auth.user.dto.LoginDto;
import com.grocery_project.auth.user.dto.RegisterDto;
import com.grocery_project.auth.user.dto.UserDataResponse;
import com.grocery_project.auth.user.entity.User;
import com.grocery_project.auth.user.mapper.UserMapper;
import com.grocery_project.auth.user.repository.UserRepository;
import com.grocery_project.config.JwtService;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService  {

    private final TokenService tokenService;
    private final JwtService jwtService;;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public BaseResponse<String> register(RegisterDto request) {
        User user = userMapper.toEntity(request);
        isUsernameExist(user);
        isEmailExist(user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive((short) 1);
        userRepository.save(user);
        return  new BaseResponse<>(List.of("User Account Created Successfully!"));
    }

    private void isUsernameExist(User user) {
        var result = userRepository.findByUsername(user.getUsername());
        if(result.isPresent()){
            throw new DuplicateRecordException("This username already exists, try another one");
        }
    }
    private void isEmailExist(User user) {
        var result = userRepository.findByEmail(user.getEmail());
        if(result.isPresent()){
            throw new DuplicateRecordException("This email already exists, try another one");
        }
    }

    @Transactional
    public UserDataResponse signin(LoginDto request) {
     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username or Password is incorrect!"));
        var jwtToken = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
       var tokenResult = tokenService.saveUserToken(user, jwtToken,jwtService.extractExpiration(jwtToken));
        UserDataResponse dto = userMapper.toDto(user);
        dto.setToken(tokenService.toDto(tokenResult));

        return  dto;
    }


    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Resource not found: " + userId));
    }
}
