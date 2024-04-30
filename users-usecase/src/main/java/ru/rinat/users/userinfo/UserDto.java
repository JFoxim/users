package ru.rinat.users.userinfo;

import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

public interface UserDto {
    Long getId();
    String getLogin();
    String getFirstName();
    String getLastName();
    String getPatronymic();
    Gender getGender();
    String getEmail();
    String getPhone();
    ZonedDateTime getDateTimeDeleted();
    Boolean getIsDeleted();

    interface Builder {
        Builder id(Long Id);
        Builder login(String login);
        Builder firstName(String firstName);
        Builder lastName(String lastName);
        Builder patronymic(String patronymic);
        Builder gender(Gender gender);
        Builder email(String email);
        Builder phone(String phone);
        Builder dateTimeDeleted(ZonedDateTime dateTimeDeleted);
        Builder isDeleted(Boolean isDeleted);
        UserDto build();

        static Builder builder() {
            return UserDefaultDto.builder();
        }
    }

}
