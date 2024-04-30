package ru.rinat.users.subscription;

import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZonedDateTime;

record SubscriptionDomainEntity(Long id, UserDomainModel creatorUser, UserDomainModel subscriberUser,
                                ZonedDateTime dateStart, ZonedDateTime dateEnd) implements SubscriptionDomainModel {

}
