package ru.rinat.users.subscription;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Builder
public class SubscriptionOperationsImpl implements SubscriptionOperations {

    private final SubscriptionMapper subscriptionMapper = Mappers.getMapper(SubscriptionMapper.class);
    private final SubscriptionDaoOperations subscriptionDaoOperations;

    @Override
    public SubscriptionDto create(SubscriptionDto subscriptionDto) {
        return Optional.ofNullable(subscriptionDto)
                .map(subscriptionMapper::toDomainModel)
                .map(subscriptionMapper::toDto)
                .map(subscriptionDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public SubscriptionDto update(SubscriptionDto subscriptionDto) {
        return Optional.ofNullable(subscriptionDto)
                .map(subscriptionMapper::toDomainModel)
                .map(subscriptionMapper::toDto)
                .map(subscriptionDaoOperations::update)
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        subscriptionDaoOperations.deleteById(id);
    }

    @Override
    public SubscriptionDto findById(Long id) {
        return subscriptionDaoOperations.findById(id);
    }

    @Override
    public List<SubscriptionDto> findAll(int page, int size, String sortDir, String sort) {
        return subscriptionDaoOperations.findAll(page, size, sortDir, sort);
    }

    @Override
    public List<SubscriptionDto> findByCreatorUserId(Long userId) {
        return subscriptionDaoOperations.findByCreatorUserId(userId);
    }
}
