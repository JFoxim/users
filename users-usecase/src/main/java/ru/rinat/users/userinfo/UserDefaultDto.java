package ru.rinat.users.userinfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class UserDefaultDto implements UserDto {
    private final Long id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Gender gender;
    private final String email;
    private final String phone;
    private final ZonedDateTime dateTimeDeleted;
    private final Boolean isDeleted;

    static class UserDefaultDtoBuilder implements Builder {}
}
