package com.example.plaza_de_comidas.adapters.driving.http.mapper;

import com.example.plaza_de_comidas.adapters.driving.http.dto.UserResponse;
import com.example.plaza_de_comidas.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserResponseMapper {
    @Mappings(value = {
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "gmail", target = "gmail"),
            @Mapping(source = "idRol", target = "idRol"),
    })
    UserResponse toUserResponse (User user);
}
