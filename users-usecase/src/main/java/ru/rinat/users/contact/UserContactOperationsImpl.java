package ru.rinat.users.contact;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import ru.rinat.users.userinfo.UserDto;

import java.util.List;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public class UserContactOperationsImpl implements UserContactOperations {

    private final UserContactMapper userContactMapper = Mappers.getMapper(UserContactMapper.class);
    private final UserContactDaoOperations userContactDaoOperations;

    @Override
    public UserContactDto create(UserContactDto userContactDto) {
        return Optional.ofNullable(userContactDto)
                .map(userContactMapper::toDomainModel)
                .map(userContactMapper::toDto)
                .map(userContactDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public UserContactDto update(UserContactDto userContactDto) {
        return Optional.ofNullable(userContactDto)
                .map(userContactMapper::toDomainModel)
                .map(userContactMapper::toDto)
                .map(userContactDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userContactDaoOperations.deleteById(id);
    }

    @Override
    public List<UserContactDto> findByUserCreator(UserDto userDto) {
        return userContactDaoOperations.findByUserCreator(userDto);
    }

    @Override
    public UserContactDto findById(Long id) {
        return userContactDaoOperations.findById(id);
    }

    @Override
    public List<UserContactDto> findByUserCreatorId(Long userId) {
        return userContactDaoOperations.findByUserCreatorId(userId);
    }
}
