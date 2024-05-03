package ru.rinat.users.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Schema(name = "Wrapper of all rest responses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto<T> implements Serializable {

   @Serial
   private static final long serialVersionUID = 3822640866873755060L;

   @JsonProperty(required = true)
   @Schema(description = "Sign of successful response", example = "true/false")
   private boolean success;

   @Schema(description = "Data of message")
   private T data;

   @Schema(description = "List containing errors")
   private List<CommonErrorsDto> errors;

   @Schema(description = "Error code")
   private String code;

   @Schema(description = "Message")
   private String message;

   private String stackTrace;
}
