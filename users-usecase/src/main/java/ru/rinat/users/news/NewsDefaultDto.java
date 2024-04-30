package ru.rinat.users.news;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class NewsDefaultDto implements NewsDto {
    private final UUID id;
    private final UserDto userCreator;
    private final String subject;
    private final String text;
    private final ZonedDateTime createDateTime;

    static class NewsDefaultDtoBuilder implements Builder {}
}
