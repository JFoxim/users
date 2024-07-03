package ru.rinat.users.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.userinfo.UserDto;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponse {
    private Long id;
    private UserDto user;
    private String value;
    private String userContactType;
}
