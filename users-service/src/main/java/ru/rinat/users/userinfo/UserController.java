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

import javax.lang.model.type.NullType;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserSpecification {

    private final UserOperations userOperations;

    private final UserRequestMapper userRequestMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Создание пользователя", description = "Позволяет создать нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь создан успешно", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> create(@RequestBody @Valid UserRequest userRequest) {
        log.info("Receive request create user: {}", userRequest);

        UserDto userDto = userOperations.create(userRequestMapper.toDto(userRequest));

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userDto.getId()).toUri();

        log.info("User {} successfully created", userRequest);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Изменение пользователя", description = "Позволяет отредактировать поля пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь изменён успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> update(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id) {
        log.info("Receive request update user {} by id {}", userRequest, id);

        userOperations.update(userRequestMapper.toDto(userRequest, id));

        log.info("User {} successfully updated", userRequest);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete/{id}")
    @Tag(name = "Удаление пользователя", description = "Позволяет удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь удалён успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> delete(@PathVariable("id") Long id) {
        log.info("Receive request delete user by id {}", id);

         userOperations.deleteById(id);

        log.info("User deleted successfully by id {}", id);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/markdeleted/{id}")
    @Tag(name = "Пометить удаленным пользователя", description = "Позволяет пометить пользователя как удалённого")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь помечен удалённым успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> markDeleted(@PathVariable("id") Long id) {
        log.info("Receive request to mark deleted user by id {}", id);

        userOperations.markDeletedById(id);

        log.info("User marked deleted successfully by id {}", id);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить пользователя по id", description = "Позволяет получить пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь найден успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<UserResponse> findById(@PathVariable("id") Long id) {
        log.info("Receive request for find user by id {}", id);

        return CommonResponseDto.<UserResponse>builder()
                .success(true)
                .data(userRequestMapper.toResponseDto(userOperations.findById(id)))
                .build();
    }

    @Override
    @GetMapping(value = "/login/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить пользователя по login", description = "Позволяет получить пользователя по login")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь найден успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<UserResponse> findByLogin(@PathVariable("login") String login) {
        return CommonResponseDto.<UserResponse>builder()
                .success(true)
                .data( userRequestMapper.toResponseDto(userOperations.findByLogin(login)))
                .build();
    }

    @Override
    @ResponseBody
    @GetMapping(value = "/all/{page}/{size}/{sortDir}/{sort}")
    public CommonResponseDto<List<UserResponse>> findAll(@PathVariable("page") int page,
                                      @PathVariable("size") int size,
                                      @PathVariable("sortDir") String sortDir,
                                      @PathVariable("sort") String sort) {
        log.info("Receive request findAll users by page {}, size {}, sortDir {}, sort {}", page, size, sortDir, sort);

        return CommonResponseDto.<List<UserResponse>>builder()
                .success(true)
                .data(userOperations.findAll(page, size, sortDir, sort).stream()
                        .map(userRequestMapper::toResponseDto).collect(Collectors.toList()))
                .build();

    }
}
