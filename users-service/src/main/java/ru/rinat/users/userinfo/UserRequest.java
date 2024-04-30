package ru.rinat.users.userinfo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class UserRequest {

    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    @NotNull
    private Gender gender;
    @NotNull
    private String email;
    private String phone;
    private ZonedDateTime dateTimeDeleted;
}
