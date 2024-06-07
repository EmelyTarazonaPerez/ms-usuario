package com.example.plaza_de_comidas.config;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter.UserAdapter;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.adapters.driven.security.adapter.JwtAdapter;
import com.example.plaza_de_comidas.adapters.driven.security.adapter.AuthenticationAdapter;
import com.example.plaza_de_comidas.adapters.driven.security.adapter.PasswordEncodeAdapter;
import com.example.plaza_de_comidas.domain.api.IAuthenticationServicePort;
import com.example.plaza_de_comidas.domain.api.IJwtServicePort;
import com.example.plaza_de_comidas.domain.api.IRegisterServicePort;
import com.example.plaza_de_comidas.domain.api.useCase.AuthenticationService;
import com.example.plaza_de_comidas.domain.api.useCase.JwtService;
import com.example.plaza_de_comidas.domain.api.useCase.RegisterService;
import com.example.plaza_de_comidas.domain.spi.IAuthenticationPort;
import com.example.plaza_de_comidas.domain.spi.IJwtPort;
import com.example.plaza_de_comidas.domain.spi.IPasswordEncodePort;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepositoryJPA userRepositoryJPA;
    private final IUserEntityMapper userEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepositoryJPA, userEntityMapper, passwordEncoder);
    }
    @Bean
    public IAuthenticationPort authenticationPort () {
        return new AuthenticationAdapter(authenticationManager);
    }
    @Bean
    public IPasswordEncodePort passwordEncodePort(){ return new PasswordEncodeAdapter();}
    @Bean
    public IRegisterServicePort userServicePort(){ return new RegisterService(userPersistencePort(), passwordEncodePort());
    }
    @Bean
    public IAuthenticationServicePort authenticationService(){return new AuthenticationService(authenticationPort());
    }
    @Bean
    public IJwtPort jwtPort () { return new JwtAdapter(); }
    @Bean
    public IJwtServicePort jwtService (){ return new JwtService(jwtPort());}
}
