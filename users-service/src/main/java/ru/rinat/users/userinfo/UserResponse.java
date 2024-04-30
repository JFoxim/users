package ru.rinat.users.userinfo;

import lombok.Getter;
import lombok.Setter;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Gender gender;
    private String email;
    private String phone;
    private ZonedDateTime dateTimeDeleted;
}
