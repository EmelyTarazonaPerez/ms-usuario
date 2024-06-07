package com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.exception.ErrorUserBd;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.plaza_de_comidas.adapters.driven.jpa.msql.utils.ContantsUtil.USER_NOT_FOUND;
import static com.example.plaza_de_comidas.adapters.driven.jpa.msql.utils.ContantsUtil.USER_NOT_REGISTER;

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

    @Override
    public User getRolUserById(int id) {
        try {
            Optional<UserEntity> userList = userRepositoryJPA.findByIdUser(id);
            if (userList.isEmpty()) throw new ErrorUserBd(USER_NOT_FOUND);
            return userEntityMapper.toUser(userList.get());

        } catch (Exception e) {
            throw new ErrorUserBd(USER_NOT_REGISTER);
        }
    }

    @Override
    public User getUserByGmail(String gmail) {
        try {
            Optional<UserEntity> user = userRepositoryJPA.findByGmail(gmail);
            return userEntityMapper.toUser(user.get());
        } catch (Exception e) {
            throw new ErrorUserBd(USER_NOT_FOUND);
        }
    }
}
