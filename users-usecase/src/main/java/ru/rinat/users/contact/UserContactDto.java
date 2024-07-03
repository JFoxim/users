package ru.rinat.users.contact;

import ru.rinat.users.userinfo.UserDto;

public interface UserContactDto {
    Long getId();
    UserDto getUser();
    String getValue();
    UserContactType getUserContactType();

    interface Builder {
        Builder id(Long Id);
        Builder user(UserDto user);
        Builder value(String value);
        Builder userContactType(UserContactType userContactType);
        UserContactDto build();
    }

    static UserContactDto.Builder builder() {
        return UserContactDefaultDto.builder();
    }
}
