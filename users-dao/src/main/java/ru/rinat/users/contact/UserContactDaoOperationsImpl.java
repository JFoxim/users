package ru.rinat.users.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rinat.users.errors.UsersServiceException;
import ru.rinat.users.userinfo.UserDaoOperations;
import ru.rinat.users.userinfo.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.rinat.users.errors.Constants.CONTACT_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserContactDaoOperationsImpl implements UserContactOperations {

    private final UserContactJpaRepository userContactJpaRepository;
    private final UserDaoOperations userDaoOperations;
    private final UserContactDtoMapper userContactDtoMapper;

    @Override
    public UserContactDto create(UserContactDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userContactDtoMapper::toEntity)
                .map(userContactJpaRepository::save)
                .map(userContactDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public UserContactDto update(UserContactDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userContactDtoMapper::toEntity)
                .map(userContactJpaRepository::save)
                .map(userContactDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public UserContactDto findById(Long id) {
        return userContactJpaRepository.findById(id)
                .map(userContactDtoMapper::toDto)
                .orElseThrow(UsersServiceException.builder()
                        .code(CONTACT_NOT_FOUND_EXCEPTION)
                        .message(String.join("News not found by id: ", id.toString()))
                        .build());
    }

    @Override
    public void deleteById(Long id) {
        userContactJpaRepository.deleteById(id);
    }

    @Override
    public List<UserContactDto> findByUserCreator(UserDto userDto) {
        return userContactJpaRepository.findByUserCreatorId(userDto.getId()).stream()
                .map(userContactDtoMapper::toDtoImpl)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserContactDto> findByUserCreatorId(Long userId) {
        return userContactJpaRepository.findByUserCreatorId(userId).stream()
                .map(userContactDtoMapper::toDtoImpl)
                .collect(Collectors.toList());
    }
}
