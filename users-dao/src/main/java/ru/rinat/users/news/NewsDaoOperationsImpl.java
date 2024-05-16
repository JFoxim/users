package ru.rinat.users.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rinat.users.userinfo.UserDaoOperationsImpl;
import ru.rinat.users.userinfo.UserDto;
import ru.rinat.users.userinfo.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsDaoOperationsImpl implements NewsOperations {

    private final NewsJpaRepository newsJpaRepository;

    private final UserDaoOperationsImpl userDaoOperations;
    private final NewsDtoMapper newsDtoMapper;

    @Override
    public NewsDto create(NewsDto newsDto) {
        return Optional.ofNullable(newsDto)
                .map(newsDtoMapper::toEntity)
                .map(newsJpaRepository::save)
                .map(newsDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        return Optional.ofNullable(newsDto)
                .map(newsDtoMapper::toEntity)
                .map(newsJpaRepository::save)
                .map(newsDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        newsJpaRepository.deleteById(id);
    }

    @Override
    public List<NewsDto> findByCreatorUser(UserDto creatorUser) {
        UserEntity userEntity = userDaoOperations.getById(creatorUser.getId());

        return newsJpaRepository.findByUserCreator(userEntity).stream()
                .map(newsDtoMapper::toDtoImpl)
                .collect(Collectors.toList());
    }
}
