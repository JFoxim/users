package ru.rinat.users.contact;

import ru.rinat.users.userinfo.UserDomainModel;

record UserContactDomainEntity(Long id, UserDomainModel user, String value,
                               String type) implements UserContactDomainModel {
}
