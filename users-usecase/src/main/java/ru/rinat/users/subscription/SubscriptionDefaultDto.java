package ru.rinat.users.subscription;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class SubscriptionDefaultDto implements SubscriptionDto {
    private final Long id;
    private final UserDto creatorUser;
    private final UserDto subscriberUser;
    private final ZonedDateTime dateStart;
    private final ZonedDateTime dateEnd;

    static class SubscriptionDefaultDtoBuilder implements SubscriptionDto.Builder {}
}
