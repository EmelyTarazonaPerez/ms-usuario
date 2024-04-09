package com.example.plaza_de_comidas.adapters.driving.http.mapper;

import com.example.plaza_de_comidas.adapters.driving.http.dto.AddUserRequest;
import com.example.plaza_de_comidas.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {
    @Mappings(value = {
            @Mapping(target = "idUser", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "identificationDocument", target = "identificationDocument"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "gmail", target = "gmail"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "idRol", target = "idRol"),
    })
    User toUser (AddUserRequest addUserRequest);
}
