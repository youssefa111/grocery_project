package com.grocery_project.config;

import com.grocery_project.core.constant.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfiguration {


    private final String[] UNSECURED_URLS = {
            AppConstants.baseUrl + "/user/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
    //    @Autowired
//    private  ExceptionHandlerFilter exceptionHandlerFilter;
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers(UNSECURED_URLS)
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )

//                .exceptionHandling(
//                        httpSecurityExceptionHandlingConfigurer ->
//                                httpSecurityExceptionHandlingConfigurer
//                                        .accessDeniedHandler((request, response, accessDeniedException) -> {
//                                          if (accessDeniedException != null){
//                                              throw  new CustomAccessDeniedHandler("Access denied");
//                                          }
//                                          else {
//                                                        throw new RuntimeException("Authentication failed");
//                                                    }
//                                        })
//                                        .authenticationEntryPoint(
//                                                (request, response, authException) -> {
//                                                    if (authException != null) {
//                                                        throw new CustomExpiredJwtException("Token has been expired!");
//                                                    } else {
//                                                        throw new RuntimeException("Authentication failed");
//                                                    }
//                                                }
//                                        )
//                )
                .sessionManagement(
                        (sessionManagement) ->
                                sessionManagement.sessionConcurrency((sessionConcurrency) ->
                                                sessionConcurrency
                                                        .maximumSessions(1)
                                                        .expiredUrl("/login?expired"))
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(exceptionHandlerFilter,JwtAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        (logout) ->
                                logout.logoutUrl(AppConstants.baseUrl + "/user/auth/logout")
                                        .addLogoutHandler(logoutHandler)
                                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
