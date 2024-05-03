package ru.rinat.users.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Structure containing errors")
public class CommonErrorsDto {

    @Schema(description = "Error code")
    private String code;

    @Schema(description = "Error message")
    private String message;
}
