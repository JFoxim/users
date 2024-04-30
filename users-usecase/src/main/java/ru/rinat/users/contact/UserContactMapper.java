package ru.rinat.users.contact;

import org.mapstruct.Mapper;
import ru.rinat.users.userinfo.UserMapper;

@Mapper(uses = { UserMapper.class })
public interface UserContactMapper {

    default UserContactDto toDto(UserContactDomainModel userContactDomainModel) {
        return mapToDtoImpl((UserContactDomainEntity) userContactDomainModel);
    }

    default UserContactDomainModel toDomainModel(UserContactDto userContactDto) {
        return mapToDomainEntity((UserContactDefaultDto) userContactDto);
    }

    UserContactDefaultDto mapToDtoImpl(UserContactDomainEntity userContactDomainEntity);

    UserContactDomainEntity mapToDomainEntity(UserContactDefaultDto userContactDefaultDto);
}
