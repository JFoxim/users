package ru.rinat.users.news;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
public class NewsOperationsImpl implements NewsOperations {

    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);
    private final NewsDaoOperations newsDaoOperations;

    @Override
    public NewsDto create(NewsDto newsDto) {
        return Optional.ofNullable(newsDto)
                .map(newsMapper::toDomainModel)
                .map(newsMapper::toDto)
                .map(newsDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        return Optional.ofNullable(newsDto)
                .map(newsMapper::toDomainModel)
                .map(newsMapper::toDto)
                .map(newsDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        newsDaoOperations.deleteById(id);
    }

    @Override
    public NewsDto findById(UUID id) {
        return newsDaoOperations.findById(id);
    }

    @Override
    public List<NewsDto> findBySubject(String login) {
        return newsDaoOperations.findBySubject(login);
    }

    @Override
    public List<NewsDto> findByCreatorUserId(Long userId) {
        return newsDaoOperations.findByCreatorUserId(userId);
    }
}
