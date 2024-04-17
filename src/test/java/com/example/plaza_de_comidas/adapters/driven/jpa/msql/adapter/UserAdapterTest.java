package com.example.plaza_de_comidas.adapters.driven.jpa.msql.adapter;

import com.example.plaza_de_comidas.adapters.driven.security.bcrypt.EncryptServiceImp;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.RolEntity;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.plaza_de_comidas.domain.model.Rol;
import com.example.plaza_de_comidas.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @Mock
    IUserRepositoryJPA userRepositoryJPA;
    @Mock
    IUserEntityMapper userEntityMapper;
    @Mock
    EncryptServiceImp encryptServiceImp;
    @InjectMocks
    UserAdapter userAdapter;

    private User proprietary;
    @BeforeEach
    void setUp() {
        proprietary = new User(
                1,
                "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                new Rol(2, "propietario", "rol"),
                "passwordEncryptada"

        );
    }

    @Test
    @DisplayName("guardar un propietario en la base de datos")
    void save() {
        UserEntity proprietaryEntity = new UserEntity(
                1,
                "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                "passwordEncryptada",
                new RolEntity(2, "propietario", "rol")
        );

        when(userEntityMapper.toUserEntity(proprietary)).thenReturn(proprietaryEntity);
        when(encryptServiceImp.encryptPassword(proprietaryEntity.getPassword())).thenReturn(proprietaryEntity.getPassword());
        when(userRepositoryJPA.save(proprietaryEntity)).thenReturn(proprietaryEntity);
        when(userEntityMapper.toUser(proprietaryEntity)).thenReturn(proprietary);

        final User result = userAdapter.save(proprietary);

        Assertions.assertEquals(result, proprietary);

    }
}