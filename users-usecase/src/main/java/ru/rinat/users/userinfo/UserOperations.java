package ru.rinat.users.userinfo;

import java.util.List;
import java.util.Map;

public interface UserOperations {

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(Long id);

    void markDeletedById(Long id);

    UserDto findById(Long id);

    UserDto findByLogin(String login);

    List<UserDto> findAll(int page, int size, String sortDir, String sort);

    List<UserDto> findByCriteriaApi(int page, int size, String sortDir, String sort, Map<String, String> map);
}
