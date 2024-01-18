package com.grocery_project.auth.token.mapper;

import com.grocery_project.auth.token.dto.TokenDto;
import com.grocery_project.auth.token.entity.Token;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapper {
    Token toEntity(TokenDto tokenDto);

    @Mapping(source = "token", target = "accessToken")
    TokenDto toDto(Token token);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Token partialUpdate(TokenDto tokenDto, @MappingTarget Token token);
}