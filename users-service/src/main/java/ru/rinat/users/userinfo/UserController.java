package ru.rinat.users.userinfo;

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

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController implements UserSpecification {

    private final UserOperations userOperations;

    private final UserRequestMapper userRequestMapper;

    @Override
    @PostMapping(value = "/user-create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Создание пользователя", description = "Позволяет создать нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь создан успешно", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> create(@RequestBody @Valid UserRequest userRequest) {
        log.info("Receive request create user: {}", userRequest);

        UserDto userDto = userOperations.create(userRequestMapper.toDto(userRequest));

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userDto.getId()).toUri();

        log.info("User {} successfully created", userRequest);

        return ResponseEntity.created(locationUri).build();
    }

    @Override
    @PutMapping(value = "/user-update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Tag(name = "Изменение пользователя", description = "Позволяет отредактировать поля пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь изменён успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public void update(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id) {
        log.info("Receive request update user {} by id {}", userRequest, id);

        userOperations.update(userRequestMapper.toDto(userRequest));

        log.info("User {} successfully updated", userRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/user-delete/{id}")
    @Tag(name = "Удаление пользователя", description = "Позволяет удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь удалён успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public void delete(@PathVariable("id") Long id) {
        log.info("Receive request delete user by id {}", id);

         userOperations.deleteById(id);

        log.info("User deleted successfully by id {}", id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/user-mark-deleted/{id}")
    @Tag(name = "Пометить удаленным пользователя", description = "Позволяет пометить пользователя как удалённого")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь помечен удалённым успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public void markDeleted(@PathVariable("id") Long id) {
        log.info("Receive request to mark deleted user by id {}", id);

        userOperations.markDeletedById(id);

        log.info("User marked deleted successfully by id {}", id);
    }

    @Override
    @GetMapping(value = "/user-get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить пользователя по id", description = "Позволяет получить пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь найден успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody UserResponse findById(@PathVariable("id") Long id) {
        log.info("Receive request for find user by id {}", id);

        return userRequestMapper.toResponseDto(userOperations.findById(id));
    }

    @Override
    @GetMapping(value = "/user-find-login/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить пользователя по login", description = "Позволяет получить пользователя по login")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь найден успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody UserResponse findByLogin(@PathVariable("login") String login) {
        return userRequestMapper.toResponseDto(userOperations.findByLogin(login));
    }

    @Override
    @ResponseBody
    @GetMapping(value = "/find-all")
    public List<UserResponse> findAll(@PathVariable("page") int page,
                                      @PathVariable("size") int size,
                                      @PathVariable("sortDir") String sortDir,
                                      @PathVariable("sort") String sort) {
        log.info("Receive request findAll users by page {}, size {}, sortDir {}, sort {}", page, size, sortDir, sort);

        return userOperations.findAll(page, size, sortDir, sort).stream()
                .map(userRequestMapper::toResponseDto).collect(Collectors.toList());
    }
}
