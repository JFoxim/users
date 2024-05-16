package ru.rinat.users.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rinat.users.userinfo.UserDaoOperationsImpl;
import ru.rinat.users.userinfo.UserDto;
import ru.rinat.users.userinfo.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContactDaoOperationsImpl implements UserContactOperations {

    private final UserContactJpaRepository userContactJpaRepository;
    private final UserDaoOperationsImpl userDaoOperations;
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
    public void deleteById(Long id) {
        userContactJpaRepository.deleteById(id);
    }

    @Override
    public List<UserContactDto> findByUserCreator(UserDto userDto) {
        UserEntity userEntity = userDaoOperations.getById(userDto.getId());

        return userContactJpaRepository.findByUser(userEntity).stream()
                .map(userContactDtoMapper::toDtoImpl)
                .collect(Collectors.toList());
    }
}
