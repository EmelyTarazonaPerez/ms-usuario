package com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.bcrypt.EncryptServiceImp;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.exception.ErrorUserBd;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private IUserRepositoryJPA userRepositoryJPA;
    private IUserEntityMapper userEntityMapper;
    private EncryptServiceImp encryptServiceImp;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        String password = userEntity.getPassword();
        userEntity.setPassword(encryptServiceImp.encryptPassword(password));
        return userEntityMapper.toUser(userRepositoryJPA.save(userEntity));
    }

    @Override
    public User getRolUserById(int id) {
        List<UserEntity> userList = userRepositoryJPA.findAll();
        return userEntityMapper.toUser(userList.stream().filter(user -> user.getIdUser() == id).toList().get(0));
    }

    @Override
    public User getUserByGmail(String gmail) {
        try {
            Optional<UserEntity> user = userRepositoryJPA.findByGmail(gmail);
            return userEntityMapper.toUser(user.get());
        } catch (Exception e) {
            throw new ErrorUserBd("Usuario no registrado");
        }
    }
}
