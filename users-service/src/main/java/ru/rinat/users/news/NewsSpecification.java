package ru.rinat.users.news;

import org.springframework.http.ResponseEntity;
import ru.rinat.users.common.CommonResponseDto;

import java.util.List;
import java.util.UUID;

public interface NewsSpecification {

    ResponseEntity<CommonResponseDto<?>> create(NewsRequest newsRequest);

    CommonResponseDto<?> update(NewsRequest newsRequest, UUID id);

    CommonResponseDto<?> delete(UUID id);

    CommonResponseDto<NewsResponse> findById(UUID id);

    CommonResponseDto<List<NewsResponse>> findBySubject(String subject);

    CommonResponseDto<List<NewsResponse>> findByUserId(Long id);
}
