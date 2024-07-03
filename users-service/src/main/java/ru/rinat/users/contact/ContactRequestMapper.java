package ru.rinat.users.contact;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rinat.users.userinfo.UserDto;


@Mapper(componentModel = "spring")
public interface ContactRequestMapper {

    default UserContactDto toDto(ContactRequest contactRequest, UserDto userDto) {
        return toDtoImpl(contactRequest, null, userDto);
    }

    default UserContactDto toDto(ContactRequest contactRequest, Long id, UserDto userDto) {
        return toDtoImpl(contactRequest, id, userDto);
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "userDto")
    UserContactDefaultDto toDtoImpl(ContactRequest contactRequest, Long id, UserDto userDto);

    default ContactResponse toResponseDto(UserContactDto userContactDto) {
        return toResponseImpl((UserContactDefaultDto)userContactDto);
    }

    ContactResponse toResponseImpl(UserContactDefaultDto userContactDefaultDto);
}
