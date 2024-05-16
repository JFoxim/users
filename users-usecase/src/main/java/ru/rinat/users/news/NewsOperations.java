package ru.rinat.users.news;

import ru.rinat.users.userinfo.UserDto;

import java.util.List;
import java.util.UUID;

public interface NewsOperations {

    NewsDto create(NewsDto userDto);

    NewsDto update(NewsDto userDto);

    void deleteById(UUID id);

    List<NewsDto> findByCreatorUser(UserDto creatorUser);
}
