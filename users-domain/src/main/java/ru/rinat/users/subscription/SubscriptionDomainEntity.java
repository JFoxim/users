package ru.rinat.users.subscription;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static ru.rinat.users.Constants.MSK_TIME_ZONE;

final class SubscriptionDomainEntity implements SubscriptionDomainModel {
    private final Long id;
    private final UserDomainModel creatorUser;
    private final UserDomainModel subscriberUser;
    private final ZonedDateTime dateStart;
    private final ZonedDateTime dateEnd;

    public SubscriptionDomainEntity(Long id, UserDomainModel creatorUser, UserDomainModel subscriberUser,
                                    ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        this.id = id;
        this.creatorUser = checkForUserDeleted(creatorUser);
        this.subscriberUser = checkForUserDeleted(subscriberUser);
        this.dateStart = dateStart == null ? ZonedDateTime.now(ZoneId.of(MSK_TIME_ZONE)) : dateStart;
        this.dateEnd = dateEnd;

        if (Objects.equals(creatorUser.getId(), subscriberUser.getId())) {
            throw new DomainModelException("Exception the user cannot follow himself");
        }
    }

    private static UserDomainModel checkForUserDeleted(UserDomainModel user) {
        if (user.getIsDeleted()) {
            throw new DomainModelException("User with id = " + user.getId() + " is marked as deleted and cannot have a subscription");
        }
        return user;
    }

    public Long getId() {
        return id;
    }

    public UserDomainModel getCreatorUser() {
        return creatorUser;
    }

    public UserDomainModel getSubscriberUser() {
        return subscriberUser;
    }

    public ZonedDateTime getDateStart() {
        return dateStart;
    }

    public ZonedDateTime getDateEnd() {
        return dateEnd;
    }
}
