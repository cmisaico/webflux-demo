package com.sermaluc.services.mappers;

import com.sermaluc.models.dtos.PhoneDto;
import com.sermaluc.models.dtos.UserRequestDTO;
import com.sermaluc.models.dtos.UserResponseDTO;
import com.sermaluc.models.entities.Phone;
import com.sermaluc.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", ignore = true )
    @Mapping(target = "lastLogin", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isActive", expression = "java(true)")
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "tokenInfo", ignore = true )
    User map(UserRequestDTO userRequestDto);

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "user", ignore = true)
    Phone map(PhoneDto phoneDto);

    @Mapping(target = "lastLogin", expression = "java(userEntity.getLastLogin().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss\")))")
    @Mapping(target = "created", expression = "java(userEntity.getCreated().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss\")))")
    @Mapping(target = "modified", expression = "java(userEntity.getModified().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss\")))")
    @Mapping(source = "tokenInfo.token", target = "token")
    @Mapping(source = "active", target = "isActive")
    UserResponseDTO map(User userEntity);

}
