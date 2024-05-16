package ru.rinat.users.userinfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class UserRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String patronymic;
    private Gender gender;
    @NotBlank
    private String email;
    private String phone;
    private ZonedDateTime dateTimeDeleted;
}
