package ru.rinat.users.userinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.rinat.users.errors.UsersServiceException;
import ru.rinat.users.rules.DateTimeRules;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.rinat.users.errors.Constants.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserDaoOperationsImpl implements UserDaoOperations {

    private final UserJpaRepository userJpaRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDto create(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userDtoMapper::toEntity)
                .map(userJpaRepository::save)
                .map(userDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public UserDto update(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userDtoMapper::toEntity)
                .map(userJpaRepository::save)
                .map(userDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void markDeletedById(Long id) {
        UserEntity userEntity = userJpaRepository.findById(id).orElseThrow();
        userEntity.setDateTimeDeleted(ZonedDateTime.now(ZoneId.of(DateTimeRules.INSTANCE.getTimeZone())));
        userJpaRepository.save(userEntity);
    }

    @Override
    public UserDto findById(Long id) {
        return Optional.ofNullable(id)
                .map(userJpaRepository::findById)
                .orElseThrow()
                .map(userDtoMapper::toDto)
                .orElseThrow(() -> UsersServiceException.builder()
                        .code(USER_NOT_FOUND_EXCEPTION)
                        .message(String.join("User not found by id: ", id.toString()))
                        .build());
    }

    @Override
    public UserDto findByLogin(String login) {
        return Optional.ofNullable(login)
                .map(userJpaRepository::findByLogin)
                .orElseThrow()
                .map(userDtoMapper::toDto)
                .orElseThrow();
    }

    public UserEntity getById(Long id) {
        return Optional.ofNullable(id)
                .map(userJpaRepository::findById)
                .orElseThrow()
                .orElseThrow(() -> UsersServiceException.builder()
                        .code(USER_NOT_FOUND_EXCEPTION)
                        .message(String.join("User not found by id: ", id.toString()))
                        .build());
    }

    @Override
    public List<UserDto> findAll(int page, int size, String sortDir, String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        return userJpaRepository.findAll(pageReq).stream()
                .map(userDtoMapper::toDtoImpl).collect(Collectors.toList());
    }
}
