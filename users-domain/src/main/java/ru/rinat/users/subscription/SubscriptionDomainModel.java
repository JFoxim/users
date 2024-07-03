package ru.rinat.users.subscription;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZonedDateTime;

public interface SubscriptionDomainModel {
    Long getId();
    UserDomainModel getCreatorUser();
    UserDomainModel getSubscriberUser();
    ZonedDateTime getDateStart();
    ZonedDateTime getDateEnd();

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
