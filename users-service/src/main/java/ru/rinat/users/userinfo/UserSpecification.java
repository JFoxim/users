package ru.rinat.users.userinfo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rinat.users.common.CommonResponseDto;

import java.util.List;
import java.util.Map;

public interface UserSpecification {

    ResponseEntity<CommonResponseDto<?>> create(UserRequest userRequest);

    CommonResponseDto<?> update(UserRequest userRequest, Long id);

    CommonResponseDto<?> delete(Long id);

    CommonResponseDto<?> markDeleted(Long id);

    CommonResponseDto<UserResponse> findById(Long id);

    CommonResponseDto<UserResponse> findByLogin(String login);

    CommonResponseDto<List<UserResponse>> findAll(int page, int size, String sortDir, String sort);

    @ResponseBody
    @GetMapping(value = "/all/{page}/{size}/{sortDir}/{sort}/{firstName}")
    @Tag(name = "Получить всех пользователей", description = "Позволяет получить всех пользователей")
    CommonResponseDto<List<UserResponse>> findByCriteriaApi(@PathVariable("page") int page,
                                                            @PathVariable("size") int size,
                                                            @PathVariable("sortDir") String sortDir,
                                                            @PathVariable("sort") String sort,
                                                            @RequestParam Map<String, String> allParams);
}
