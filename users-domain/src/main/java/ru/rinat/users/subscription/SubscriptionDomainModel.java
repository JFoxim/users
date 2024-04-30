package ru.rinat.users.subscription;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZonedDateTime;

public interface SubscriptionDomainModel {
    Long id();
    UserDomainModel creatorUser();
    UserDomainModel subscriberUser();
    ZonedDateTime dateStart();
    ZonedDateTime dateEnd();

    interface Builder {
        Builder id(Long Id);
        Builder creatorUser(UserDomainModel creatorUser);
        Builder subscriberUser(UserDomainModel subscriberUser);
        Builder dateStart(ZonedDateTime dateStart);
        Builder dateEnd(ZonedDateTime dateEnd);
        SubscriptionDomainModel build() throws DomainModelException;

        static Builder builder() {
            return new SubscriptionDomainModelBuilder();
        }
    }
}
