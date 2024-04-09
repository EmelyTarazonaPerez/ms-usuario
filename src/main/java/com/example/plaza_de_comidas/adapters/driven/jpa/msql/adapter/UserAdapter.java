package com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private IUserRepositoryJPA userRepositoryJPA;
    private IUserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        return userEntityMapper.toUser(userRepositoryJPA.save(userEntity));
    }
}
