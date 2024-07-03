package ru.rinat.users.news;

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
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController implements NewsSpecification {

    private final NewsOperations newsOperations;

    private final UserOperations userOperations;

    private final NewsRequestMapper newsRequestMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Создание новости", description = "Позволяет создать новость")
    @ApiResponses(value = {
            @ApiResponse(description = "Новость создана успешно", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CommonResponseDto<?>> create(@RequestBody @Valid NewsRequest newsRequest) {
        log.info("Receive request create news: {}", newsRequest);

        UserDto userDto = userOperations.findById(newsRequest.getUserCreatorId());
        NewsDto newsDto = newsOperations.create(newsRequestMapper.toDto(newsRequest, userDto));

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newsDto.getId()).toUri();


        log.info("News {} successfully created", newsRequest);

        return ResponseEntity
                .created(locationUri)
                .body(CommonResponseDto.<NullType>builder()
                        .success(true)
                        .build());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Изменение новости", description = "Позволяет отредактировать поля новости")
    @ApiResponses(value = {
            @ApiResponse(description = "Новость изменёна успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Новость не найдена", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> update(@RequestBody @Valid NewsRequest newsRequest, @PathVariable UUID id) {
        log.info("Receive request update news {} by id {}", newsRequest, id);

        UserDto userDto = userOperations.findById(newsRequest.getUserCreatorId());
        newsOperations.update(newsRequestMapper.toDto(newsRequest, id, userDto));

        log.info("User {} successfully updated", newsRequest);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @Tag(name = "Удаление новости", description = "Позволяет удалить новость")
    @ApiResponses(value = {
            @ApiResponse(description = "Новость удалён успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> delete(@PathVariable("id") UUID id) {
        log.info("Receive request delete news by id {}", id);

        newsOperations.deleteById(id);

        log.info("News deleted successfully by id {}", id);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }


    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить новость по id", description = "Позволяет получить новость по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(description = "Пользователь найден успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<NewsResponse> findById(@PathVariable("id") UUID id) {
        log.info("Receive request for find news by id {}", id);
        return CommonResponseDto.<NewsResponse>builder()
                .success(true)
                .data(newsRequestMapper.toResponseDto(newsOperations.findById(id)))
                .build();
    }

    @Override
    @GetMapping(value = "/subject/{subject}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить новость по subject", description = "Позволяет получить новость по теме")
    @ApiResponses(value = {
            @ApiResponse(description = "Новость найдена успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<List<NewsResponse>> findBySubject(@PathVariable("subject") String subject) {
        return CommonResponseDto.<List<NewsResponse>>builder()
                .success(true)
                .data(newsOperations.findBySubject(subject).stream()
                        .map(newsRequestMapper::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    @ResponseBody
    @GetMapping(value = "/userid/{userId}")
    @Tag(name = "Получить новости по userId", description = "Позволяет получить список новостей по идентификатору пользователя")
    public CommonResponseDto<List<NewsResponse>> findByUserId(@PathVariable("userId") Long userId) {
        log.info("Receive request findByUserId news by {}", userId);

        return CommonResponseDto.<List<NewsResponse>>builder()
                .success(true)
                .data(newsOperations.findByCreatorUserId(userId).stream()
                        .map(newsRequestMapper::toResponseDto).collect(Collectors.toList()))
                .build();

    }
}
