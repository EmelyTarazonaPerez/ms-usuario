package com.example.plaza_de_comidas.config;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter.UserAdapter;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.bcrypt.EncryptServiceImp;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.api.useCase.UserCase;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepositoryJPA userRepositoryJPA;
    private final IUserEntityMapper userEntityMapper;
    private final EncryptServiceImp encryptServiceImp;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepositoryJPA, userEntityMapper, encryptServiceImp);
    }
    @Bean
    public IUserServicePort userServicePort(){ return new UserCase(userPersistencePort());
    }
}
