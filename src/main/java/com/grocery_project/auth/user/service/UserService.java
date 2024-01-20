package com.grocery_project.auth.user.service;

import com.grocery_project.auth.token.service.TokenService;
import com.grocery_project.auth.user.dto.ChangePasswordRequest;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final TokenService tokenService;
    private final JwtService jwtService;
    ;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public BaseResponse<String> register(RegisterDto request) {
        User user = userMapper.toEntity(request);
        isEmailExist(user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive((short) 1);
        userRepository.save(user);
        return new BaseResponse<>(null, "User Account Created Successfully!");
    }

    private void isEmailExist(User user) {
        var result = userRepository.findByEmail(user.getEmail());
        if (result.isPresent()) {
            throw new DuplicateRecordException("This email already exists, try another one");
        }
    }

    @Transactional
    public UserDataResponse signin(LoginDto request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException("Email or Password is incorrect!"));
            var accessToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            tokenService.revokeAllUserTokens(user);
            var tokenResult = tokenService.saveUserToken(user, accessToken, jwtService.extractExpiration(accessToken));
            UserDataResponse dto = userMapper.toDto(user);
            var tokenDTO = tokenService.toDto(tokenResult);
            tokenDTO.setRefreshToken(refreshToken);
            dto.setToken(tokenDTO);

            return dto;
        } catch (AuthenticationException ex) {
            System.out.println("testHere Before");
            throw new BadCredentialsException("Email or Password is incorrect!");
        }

    }


    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Resource not found: " + userId));
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        log.debug(user.toString());

        // check if the current password is correct
        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.newPassword()));

        // save the new password
        userRepository.save(user);
    }
}
