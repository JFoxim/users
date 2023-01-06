package com.skillbox.users.mapper;

import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto convert(User user);
}
