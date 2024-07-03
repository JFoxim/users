package ru.rinat.users.contact;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.rinat.users.userinfo.UserDto;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class UserContactDefaultDto implements UserContactDto {
    private final Long id;
    private final UserDto user;
    private final String value;
    private final UserContactType userContactType;

    static class UserContactDefaultDtoBuilder implements Builder {}
}
