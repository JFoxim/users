package ru.rinat.users.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsRequest {
    @NotNull
    private Long userCreatorId;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
    private ZonedDateTime createDateTime;
}
