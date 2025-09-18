package ru.rinat.users.userinfo;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public class UserOperationsImpl implements UserOperations {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserDaoOperations userDaoOperations;

    @Override
    public UserDto create(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userMapper::toDomainModel)
                .map(userMapper::toDto)
                .map(userDaoOperations::create)
                .orElseThrow();
    }

    @Override
    public UserDto update(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userMapper::toDomainModel)
                .map(userMapper::toDto)
                .map(userDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userDaoOperations.deleteById(id);
    }

    @Override
    public void markDeletedById(Long id) {
        userDaoOperations.markDeletedById(id);
    }

    @Override
    public UserDto findById(Long id) {
        return userDaoOperations.findById(id);
    }

    @Override
    public UserDto findByLogin(String login) {
        return userDaoOperations.findByLogin(login);
    }

    @Override
    public List<UserDto> findAll(int page, int size, String sortDir, String sort) {
        return userDaoOperations.findAll(page, size, sortDir, sort);
    }

    @Override
    public List<UserDto> findByCriteriaApi(int page, int size, String sortDir, String sort, Map<String, String> map) {
        return userDaoOperations.findByCriteriaApi(page, size, sortDir, sort, map);
    }
}
