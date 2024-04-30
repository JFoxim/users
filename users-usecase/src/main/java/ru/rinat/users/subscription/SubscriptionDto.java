package ru.rinat.users.subscription;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;

public interface SubscriptionDto {
    Long getId();
    UserDto getCreatorUser();
    UserDto getSubscriberUser();
    ZonedDateTime getDateStart();
    ZonedDateTime getDateEnd();

    interface Builder {
        Builder id(Long Id);
        Builder creatorUser(UserDto creatorUser);
        Builder subscriberUser(UserDto subscriberUser);
        Builder dateStart(ZonedDateTime dateStart);
        Builder dateEnd(ZonedDateTime dateEnd);
        SubscriptionDto build() throws DomainModelException;

        static Builder builder() {
            return SubscriptionDefaultDto.builder() ;
        }
    }
}
