package ru.rinat.users.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactRequest {
    @NotNull
    private Long userId;
    @NotBlank
    private String value;
    @NotBlank
    private String userContactType;
}
