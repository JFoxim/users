package ru.rinat.users.userinfo;

import java.util.List;

public interface UserOperations {

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(Long id);

    void markDeletedById(Long id);

    UserDto findById(Long id);

    UserDto findByLogin(String login);

    List<UserDto> findAll(int page, int size, String sortDir, String sort);
}
