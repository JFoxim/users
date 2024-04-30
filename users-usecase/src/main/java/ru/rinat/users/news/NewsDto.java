package ru.rinat.users.news;

import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface NewsDto {
    UUID getId();
    UserDto getUserCreator();
    String getSubject();
    String getText();
    ZonedDateTime getCreateDateTime();

    interface Builder {
        Builder id(UUID Id);
        Builder userCreator(UserDto userCreator);
        Builder subject(String subject);
        Builder text(String text);
        Builder createDateTime(ZonedDateTime createDateTime);
        NewsDto build();
    }

    static NewsDto.Builder builder() {
        return NewsDefaultDto.builder() ;
    }
}
