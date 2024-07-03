package ru.rinat.users.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.userinfo.UserDto;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionResponse {
    private Long id;
    private UserDto creatorUser;
    private UserDto subscriberUser;
    private ZonedDateTime dateStart;
    private ZonedDateTime dateEnd;
}
