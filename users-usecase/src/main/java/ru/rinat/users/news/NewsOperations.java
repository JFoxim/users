package ru.rinat.users.news;

import java.util.List;
import java.util.UUID;

public interface NewsOperations {

    NewsDto create(NewsDto newsDto);

    NewsDto update(NewsDto newsDto);

    void deleteById(UUID id);

    NewsDto findById(UUID id);

    List<NewsDto> findBySubject(String subject);

    List<NewsDto> findByCreatorUserId(Long userId);
}
