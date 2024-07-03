package ru.rinat.users.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.rinat.users.userinfo.UserDaoOperationsImpl;
import ru.rinat.users.userinfo.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubDaoOperationsImpl implements SubscriptionDaoOperations {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    private final UserDaoOperationsImpl userDaoOperations;
    private final SubscriptionDtoMapper subscriptionDtoMapper;

    @Override
    public SubscriptionDto create(SubscriptionDto subscriptionDto) {
        return Optional.ofNullable(subscriptionDto)
                .map(subscriptionDtoMapper::toEntity)
                .map(subscriptionJpaRepository::save)
                .map(subscriptionDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public SubscriptionDto update(SubscriptionDto subscriptionDto) {
        return Optional.ofNullable(subscriptionDto)
                .map(subscriptionDtoMapper::toEntity)
                .map(subscriptionJpaRepository::save)
                .map(subscriptionDtoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
       subscriptionJpaRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionDto> findByCreatorUserId(Long creatorUserId) {
        UserEntity userEntity = userDaoOperations.getById(creatorUserId);

        return subscriptionJpaRepository.findByCreatorUser(userEntity).stream()
                .map(subscriptionDtoMapper::toDtoImpl)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto findById(Long subscriptionId) {
        return subscriptionDtoMapper.toDto(
                subscriptionJpaRepository.findById(subscriptionId)
                .orElseThrow());
    }

    @Override
    public List<SubscriptionDto> findAll(int page, int size, String sortDir, String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        return subscriptionJpaRepository.findAll(pageReq).stream()
                .map(subscriptionDtoMapper::toDtoImpl).collect(Collectors.toList());
    }
}
