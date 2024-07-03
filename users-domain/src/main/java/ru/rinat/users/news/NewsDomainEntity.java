package ru.rinat.users.news;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static ru.rinat.users.Constants.MSK_TIME_ZONE;

final class NewsDomainEntity implements NewsDomainModel {
    private final UUID id;
    private final UserDomainModel userCreator;
    private final String subject;
    private final String text;
    private final ZonedDateTime createDateTime;

    public NewsDomainEntity(UUID id, UserDomainModel userCreator, String subject, String text, ZonedDateTime createDateTime) {
        this.id = id;
        this.userCreator = checkForUserDeleted(userCreator);
        this.subject = subject;
        this.text = text;
        this.createDateTime = createDateTime == null ? ZonedDateTime.now(ZoneId.of(MSK_TIME_ZONE)) : createDateTime;
    }

    private static UserDomainModel checkForUserDeleted(UserDomainModel user) {
        if (user.getIsDeleted()) {
            throw new DomainModelException("User with id = " + user.getId() + " is marked as deleted and cannot add a news");
        }
        return user;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UserDomainModel getUserCreator() {
        return userCreator;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }
}
