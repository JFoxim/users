package ru.rinat.users.subscription;

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
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscripionController implements SubscriptionSpecification {

    private final SubscriptionOperations subscriptionOperations;

    private final UserOperations userOperations;

    private final SubscriptionRequestMapper subscriptionRequestMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Создание подписки", description = "Позволяет создать подписку")
    @ApiResponses(value = {
            @ApiResponse(description = "Подписка создана успешно", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CommonResponseDto<?>> create(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        log.info("Receive request create subscription: {}", subscriptionRequest);

        UserDto userCreatorDto = userOperations.findById(subscriptionRequest.getUserCreatorId());
        UserDto userSubscriberDto = userOperations.findById(subscriptionRequest.getSubscriberUserId());
        SubscriptionDto subscriptionDto = subscriptionOperations.create(
                subscriptionRequestMapper.toDto(subscriptionRequest, userCreatorDto, userSubscriberDto));

        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(subscriptionDto.getId()).toUri();


        log.info("Subscription {} successfully created", subscriptionRequest);

        return ResponseEntity
                .created(locationUri)
                .body(CommonResponseDto.<NullType>builder()
                        .success(true)
                        .build());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Изменение подписки", description = "Позволяет отредактировать поля подписки")
    @ApiResponses(value = {
            @ApiResponse(description = "Подписка изменёна успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Подписка не найдена", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> update(@RequestBody @Valid SubscriptionRequest subscriptionRequest, @PathVariable Long id) {
        log.info("Receive request update subscription {} by id {}", subscriptionRequest, id);

        UserDto userCreatorDto = userOperations.findById(subscriptionRequest.getUserCreatorId());
        UserDto userSubscriberDto = userOperations.findById(subscriptionRequest.getSubscriberUserId());
        subscriptionOperations.update(subscriptionRequestMapper
                .toDto(subscriptionRequest, id, userCreatorDto, userSubscriberDto));

        log.info("Subscription {} successfully updated", subscriptionRequest);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @Tag(name = "Удаление подписки", description = "Позволяет удалить подписку")
    @ApiResponses(value = {
            @ApiResponse(description = "Подписка удалёна успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Подписка не найдена", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public CommonResponseDto<?> delete(@PathVariable("id") Long id) {
        log.info("Receive request delete subscription by id {}", id);

        subscriptionOperations.deleteById(id);

        log.info("Subscription deleted successfully by id {}", id);

        return CommonResponseDto.<NullType>builder()
                .success(true)
                .build();
    }


    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Получить подписку по id", description = "Позволяет получить новость по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(description = "Подписка найдена успешно", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
    })
    public @ResponseBody CommonResponseDto<SubscriptionResponse> findById(@PathVariable("id") Long id) {
        log.info("Receive request for find news by id {}", id);
        return CommonResponseDto.<SubscriptionResponse>builder()
                .success(true)
                .data(subscriptionRequestMapper.toResponseDto(subscriptionOperations.findById(id)))
                .build();
    }


    @Override
    @ResponseBody
    @GetMapping(value = "/userid/{userId}")
    @Tag(name = "Получить список подписок по userCreateId", description = "Позволяет получить список подписок по идентификатору пользователя")
    public CommonResponseDto<List<SubscriptionResponse>> findByUserCreatorId(@PathVariable("userId") Long userId) {
        log.info("Receive request findByUserId subscription by {}", userId);

        return CommonResponseDto.<List<SubscriptionResponse>>builder()
                .success(true)
                .data(subscriptionOperations.findByCreatorUserId(userId).stream()
                        .map(subscriptionRequestMapper::toResponseDto).collect(Collectors.toList()))
                .build();

    }

    @Override
    @ResponseBody
    @GetMapping(value = "/all/{page}/{size}/{sortDir}/{sort}")
    @Tag(name = "Получить все подписки", description = "Позволяет получить все подписки")
    public CommonResponseDto<List<SubscriptionResponse>> findAll(@PathVariable("page") int page,
                                                         @PathVariable("size") int size,
                                                         @PathVariable("sortDir") String sortDir,
                                                         @PathVariable("sort") String sort) {
        log.info("Receive request findAll subscriptions by page {}, size {}, sortDir {}, sort {}", page, size, sortDir, sort);

        return CommonResponseDto.<List<SubscriptionResponse>>builder()
                .success(true)
                .data(subscriptionOperations.findAll(page, size, sortDir, sort).stream()
                        .map(subscriptionRequestMapper::toResponseDto).collect(Collectors.toList()))
                .build();

    }
}
