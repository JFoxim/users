package com.skillbox.users.mapper;

import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User user);
}
