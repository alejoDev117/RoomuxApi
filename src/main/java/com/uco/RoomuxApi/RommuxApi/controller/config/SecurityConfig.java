package com.uco.RoomuxApi.RommuxApi.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").permitAll()  // Permite todas las peticiones a /api/**
                                .anyRequest().authenticated()  // Requiere autenticación para cualquier otra petición
                )
                .csrf(csrf -> csrf.disable());  // Desactiva CSRF para simplificar el desarrollo

        return http.build();
    }
}
