package ru.rinat.users.contact;

import ru.rinat.users.userinfo.UserDto;

import java.util.List;

public interface UserContactOperations {

    UserContactDto create(UserContactDto userDto);

    UserContactDto update(UserContactDto userDto);

    void deleteById(Long id);

    List<UserContactDto> findByUserCreator(UserDto userDto);
}
