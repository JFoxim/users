package ru.rinat.users.news;

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

    public NewsDomainEntity(UUID id, UserDomainModel userCreator, String subject, String text) {
        this.id = id;
        this.userCreator = userCreator;
        this.subject = subject;
        this.text = text;
        this.createDateTime = ZonedDateTime.now(ZoneId.of(MSK_TIME_ZONE));
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
