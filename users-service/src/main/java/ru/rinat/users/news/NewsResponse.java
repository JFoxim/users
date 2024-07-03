package ru.rinat.users.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {
    private UUID id;
    private UserDto userCreator;
    private String subject;
    private String text;
    private ZonedDateTime createDateTime;
}
