package ru.rinat.users.userinfo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

   default UserDto toDto(UserRequest userRequest) {
      return toDtoImpl(userRequest, null);
   }

   default UserDto toDto(UserRequest userRequest, Long id) {
       return toDtoImpl(userRequest, id);
   }

   @Mapping(target = "isDeleted", ignore = true)
   UserDefaultDto toDtoImpl(UserRequest userRequest, Long id);

   default UserResponse toResponseDto(UserDto userDto) {
      return toResponseImpl((UserDefaultDto)userDto);
   }

   UserResponse toResponseImpl(UserDefaultDto userDefaultDto);

}
