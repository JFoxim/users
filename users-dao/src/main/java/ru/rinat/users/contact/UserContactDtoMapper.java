package ru.rinat.users.contact;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.rinat.users.userinfo.UserDtoMapper;

@Mapper(componentModel = "spring")
public interface UserContactDtoMapper {

    UserDtoMapper USER_DTO_MAPPER = Mappers.getMapper(UserDtoMapper.class);

    default UserContactInfoEntity toEntity(UserContactDto userContactDto) {
        return toEntityByImpl((UserContactDefaultDto) userContactDto);
    }

    UserContactInfoEntity toEntityByImpl(UserContactDefaultDto userContactDefaultDto);

    default UserContactDto toDto(UserContactInfoEntity userContactInfoEntity) {
        return toDtoImpl(userContactInfoEntity);
    }

    @Mapping(target = "user", expression = "java(USER_DTO_MAPPER.toDto(userContactInfoEntity.getUser()))")
    UserContactDefaultDto toDtoImpl(UserContactInfoEntity userContactInfoEntity);
}
