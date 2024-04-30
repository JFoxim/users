package ru.rinat.users.contact;

import ru.rinat.users.DomainModelException;
import ru.rinat.users.userinfo.UserDto;

public interface UserContactDto {
    Long getId();
    UserDto getUser();
    String getValue();
    String getType();

    interface Builder {
        Builder id(Long Id);
        Builder user(UserDto user);
        Builder value(String value);
        Builder type(String type);
        UserContactDto build() throws DomainModelException;
    }

    static Builder builder() {
        return UserContactDto.builder();
    }
}
