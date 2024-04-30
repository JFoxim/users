package ru.rinat.users.contact;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rinat.users.userinfo.UserDto;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class UserContactDefaultDto implements UserContactDto {
    private final Long id;
    private final UserDto user;
    private final String value;
    private final String type;

    static class UserContactDefaultDtoBuilder implements UserContactDto.Builder {}
}
