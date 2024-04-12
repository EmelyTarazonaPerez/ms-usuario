package com.example.plaza_de_comidas.adapters.driven.jpa.msql.mapper;


import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.plaza_de_comidas.domain.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    @Mappings(value = {
            @Mapping(source = "idUser",  target = "idUser"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "identificationDocument", target = "identificationDocument"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "gmail", target = "gmail"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "idRol", target = "idRol"),
    })
    User toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}
