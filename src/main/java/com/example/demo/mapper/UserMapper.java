package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity (UserDto userDto);
    UserDto toUserDto (UserEntity userEntity);
}
