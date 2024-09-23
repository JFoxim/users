package ru.rinat.users.contact;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.rinat.users.common.CommonResponseDto;
import ru.rinat.users.userinfo.UserDto;
import ru.rinat.users.userinfo.UserOperations;

import javax.lang.model.type.NullType;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController implements ContactSpecification {

    private final UserContactOperations userContactOperations;

    private final UserOperations userOperations;

    private final ContactRequestMapper contactRequestMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Добавление контактной информации", description = "Позволяет добавить контактной информации")
    @ApiResponses(value = {
            @ApiResponse(description = "Контактная информация создана успешно", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CommonResponseDto<?>> create(@RequestBody @Valid ContactRequest contactRequest) {
        log.info("Receive request create user contact: {}", contactRequest);

        UserDto userDto = userOperations.findById(contactRequest.getUserId());
        UserContactDto userContactDto = userContactOperations.create(contactRequestMapper.toDto(contactRequest, userDto));

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userContactDto.getId()).toUri();

        log.info("User contact {} successfully created", contactRequest);

        return ResponseEntity
                .created(locationUri)
                .body(CommonResponseDto.<NullType>builder()
                        .success(true)
                        .build());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Изменение контактной информации", description = "Позволяет отредактировать поля контактной информации")
    @ApiResponses(value = {
            @ApiResponse(description = "Контактная информация изменёна успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Контактная информация не найдена", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> update(@RequestBody @Valid ContactRequest contactRequest, @PathVariable Long id) {
        log.info("Receive request update user contact {} by id {}", contactRequest, id);

        UserDto userDto = userOperations.findById(contactRequest.getUserId());
        userContactOperations.update(contactRequestMapper.toDto(contactRequest, id, userDto));

        log.info("User contact {} successfully updated", contactRequest);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @Tag(name = "Удаление контактной информации", description = "Позволяет удалить контактную информацию")
    @ApiResponses(value = {
            @ApiResponse(description = "Контактная информация удалёна успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Контактная информация не найдена", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> delete(@PathVariable("id") Long id) {
        log.info("Receive request delete user contact by id {}", id);

        userContactOperations.deleteById(id);

        log.info("User contact deleted successfully by id {}", id);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }


    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить контакную информацию по id", description = "Позволяет получить контакную информацию по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(description = "Контакная информация найдена успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<ContactResponse> findById(@PathVariable("id") Long id) {
        log.info("Receive request for find user contact by id {}", id);
        return CommonResponseDto.<ContactResponse>builder()
                .success(true)
                .data(contactRequestMapper.toResponseDto(userContactOperations.findById(id)))
                .build();
    }


    @Override
    @ResponseBody
    @GetMapping(value = "/userid/{userId}")
    @Tag(name = "Получить список контакной информации по userCreateId", description = "Позволяет получить список контакной информации по userCreateId")
    public CommonResponseDto<List<ContactResponse>> findByUserCreatorId(@PathVariable("userId") Long userId) {
        log.info("Receive request findByUserId subscription by {}", userId);

        return CommonResponseDto.<List<ContactResponse>>builder()
                .success(true)
                .data(userContactOperations.findByUserCreatorId(userId).stream()
                        .map(contactRequestMapper::toResponseDto).collect(Collectors.toList()))
                .build();
    }
}
