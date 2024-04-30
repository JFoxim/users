package ru.rinat.users.contact;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDomainModel;

public interface UserContactDomainModel {
    Long id();
    UserDomainModel user();
    String value();
    String type();

    interface Builder {
        Builder id(Long Id);
        Builder user(UserDomainModel user);
        Builder value(String value);
        Builder type(String type);
        UserContactDomainModel build() throws DomainModelException;

        static Builder builder() {
            return new UserContactDomainModelBuilder();
        }
    }
}
