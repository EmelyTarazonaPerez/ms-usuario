package com.example.plaza_de_comidas.adapters.driven.security.config;


import com.example.plaza_de_comidas.domain.spi.IJwtPort;
import com.example.plaza_de_comidas.adapters.driven.security.jwt.filters.JwtAuthenticationFilter;
import com.example.plaza_de_comidas.adapters.driven.security.adapter.JwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAdapter tokenValidator;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/create/customer").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/create/owner").hasAuthority("admin")
                        .requestMatchers(HttpMethod.POST, "/auth/create/employee").hasAuthority("propietario")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new IJwtPort.JwtTokenValidationFilter(tokenValidator), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

