package ru.rinat.users.userinfo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

   default UserDto toDto(UserRequest userRequest) {
       return toDtoImpl(userRequest);
   }

   @Mapping(target = "isDeleted", ignore = true)
   @Mapping(target = "id", ignore = true)
   UserDefaultDto toDtoImpl(UserRequest userRequest);

   default UserResponse toResponseDto(UserDto userDto) {
      return toResponseImpl((UserDefaultDto)userDto);
   }

   UserResponse toResponseImpl(UserDefaultDto userDefaultDto);

}
