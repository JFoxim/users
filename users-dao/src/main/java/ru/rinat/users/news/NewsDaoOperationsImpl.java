package ru.rinat.users.news;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rinat.users.errors.UsersServiceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.rinat.users.errors.Constants.NEWS_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class NewsDaoOperationsImpl implements NewsDaoOperations {

    private final NewsJpaRepository newsJpaRepository;

    private final NewsDtoMapper newsDtoMapper;

    @Override
    public NewsDto create(@Nonnull NewsDto newsDto) {
       return Optional.ofNullable(newsDto)
                .map(newsDtoMapper::toEntity)
                .map(newsJpaRepository::save)
                .map(newsDtoMapper::toDto)
                .orElseThrow(UsersServiceException.builder()
                        .code(NEWS_NOT_FOUND_EXCEPTION)
                        .message(String.join("News not found during creation ", newsDto != null ? newsDto.toString() : ""))
                        .build());
    }

    @Override
    public NewsDto update(@Nonnull NewsDto newsDto) {
        return Optional.ofNullable(newsDto)
                .map(newsDtoMapper::toEntity)
                .map(newsJpaRepository::save)
                .map(newsDtoMapper::toDto)
                .orElseThrow(UsersServiceException.builder()
                        .code(NEWS_NOT_FOUND_EXCEPTION)
                        .message(String.join("News not found when updating ", newsDto != null ? newsDto.toString() : ""))
                        .build());
    }

    @Override
    public void deleteById(UUID id) {
        newsJpaRepository.deleteById(id);
    }

    @Override
    public NewsDto findById(UUID id) {
        return newsJpaRepository.findById(id)
                .map(newsDtoMapper::toDto)
                .orElseThrow(UsersServiceException.builder()
                        .code(NEWS_NOT_FOUND_EXCEPTION)
                        .message(String.join("News not found by id: ", id.toString()))
                        .build());
    }

    @Override
    public List<NewsDto> findBySubject(String subject) {
        return newsJpaRepository.findBySubjectContaining(subject).stream()
                .map(newsDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> findByCreatorUserId(Long userId) {
        return newsJpaRepository.findByUserCreatorId(userId).stream()
                .map(newsDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
