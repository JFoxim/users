package ru.rinat.users.userinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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
