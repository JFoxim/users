package ru.rinat.users.contact;

import ru.rinat.users.userinfo.UserDto;

import java.util.List;

public interface UserContactDaoOperations {

    UserContactDto create(UserContactDto userContactDto);

    UserContactDto update(UserContactDto userContactDto);

    UserContactDto findById(Long id);

    void deleteById(Long id);

    List<UserContactDto> findByUserCreator(UserDto userDto);

    List<UserContactDto> findByUserCreatorId(Long userId);

    List<UserContactDto> findAll(int page, int size, String sortDir, String sort);
}
