package ru.rinat.users.news;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface NewsDomainModel {
    UUID getId();
    UserDomainModel getUserCreator();
    String getSubject();
    String getText();
    ZonedDateTime getCreateDateTime();

    interface Builder {
        NewsDomainModel.Builder id(UUID Id);
        NewsDomainModel.Builder userCreator(UserDomainModel userCreator);
        NewsDomainModel.Builder subject(String subject);
        NewsDomainModel.Builder text(String text);
        NewsDomainModel.Builder createDateTime(ZonedDateTime createDateTime);
        NewsDomainModel build() throws DomainModelException;

        static NewsDomainModel.Builder builder() {
            return new NewsDomainModelBuilder();
        }
    }
}
