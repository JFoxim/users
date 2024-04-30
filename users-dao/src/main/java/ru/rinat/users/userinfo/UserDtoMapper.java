package ru.rinat.users.userinfo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

   default UserEntity toEntity(UserDto userDto) {
       return toEntityByImpl((UserDefaultDto) userDto);
   }

   UserEntity toEntityByImpl(UserDefaultDto userDefaultUnit);

   default UserDto toDto(UserEntity userEntity) {
       return toDtoImpl(userEntity);
   }

   @Mapping(target = "isDeleted", ignore = true)
   UserDefaultDto toDtoImpl(UserEntity userEntity);

}
