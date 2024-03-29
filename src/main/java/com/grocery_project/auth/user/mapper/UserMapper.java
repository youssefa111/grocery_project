package com.grocery_project.auth.user.mapper;

import com.grocery_project.auth.role.mapper.RoleMapper;
import com.grocery_project.auth.token.mapper.TokenMapper;
import com.grocery_project.auth.user.dto.RegisterDto;
import com.grocery_project.auth.user.dto.UserDataResponse;
import com.grocery_project.auth.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                RoleMapper.class,
                TokenMapper.class
        }

)
public interface UserMapper {


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roleId", source = "user.role.id")
    UserDataResponse toDto(User user);

    @Mapping(target = "role.id", source = "registerDto.roleId")
    User toEntity(RegisterDto registerDto);

//    RegisterDto mapUserEntityToRegisterDto(User user);

}
