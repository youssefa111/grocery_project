package com.grocery_project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery_project.auth.token.repository.TokenRepository;
import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.CustomExpiredJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//@Order(2)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private  final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userName;
            if(authHeader == null || !authHeader.startsWith("Bearer "))
            {
                filterChain.doFilter(request,response);
                return;
            }

                jwt = authHeader.substring(7);
                userName = jwtService.extractUsername(jwt);
                if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                    var isTokenValid = tokenRepository.findByToken(jwt).map( t -> !t.isExpired() && !t.isRevoked()).orElse(false);
                    if(jwtService.isTokenValid(jwt,userDetails) && isTokenValid ){
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request,response);
            }catch(JwtException e){
            // TRY throw JwtException and give it message and handle it in ExceptionController
            BaseResponse errorResponse = new BaseResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.name(), Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
            }

        }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    }
