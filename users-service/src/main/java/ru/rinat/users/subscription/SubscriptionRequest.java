package ru.rinat.users.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRequest {
    @NotNull
    private Long userCreatorId;
    @NotNull
    private Long subscriberUserId;
    private ZonedDateTime dateStart;
    private ZonedDateTime dateEnd;
}
