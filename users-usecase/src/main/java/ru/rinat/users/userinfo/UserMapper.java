package ru.rinat.users.userinfo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    default UserDto toDto(UserDomainModel userDomainModel) {
         return mapToDtoImpl((UserDomainEntity)userDomainModel);
    }

    default UserDomainModel toDomainModel(UserDto userDto) {
          return mapToDomainEntity((UserDefaultDto) userDto);
    }


    @Mapping(target = "id", source = "id")
    @Mapping(target = "isDeleted", source = "isDeleted")
    UserDefaultDto mapToDtoImpl(UserDomainEntity userDomainEntity);

    UserDomainEntity mapToDomainEntity(UserDefaultDto userDefaultUnit);
}
