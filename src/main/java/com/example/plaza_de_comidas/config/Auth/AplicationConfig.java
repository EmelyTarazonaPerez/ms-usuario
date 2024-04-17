package com.example.plaza_de_comidas.config.Auth;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final IUserRepositoryJPA userRepository;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvide = new DaoAuthenticationProvider();
        authProvide.setUserDetailsService(userDetailsService());
        authProvide.setPasswordEncoder(passwordEncoder());
        return authProvide;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return gmail -> userRepository.findByGmail(gmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

