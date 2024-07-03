package ru.rinat.users.subscription;

import java.util.List;

public interface SubscriptionOperations {

    SubscriptionDto create(SubscriptionDto subscriptionDto);

    SubscriptionDto update(SubscriptionDto subscriptionDto);

    void deleteById(Long id);

    List<SubscriptionDto> findByCreatorUserId(Long userCreatorId);

    SubscriptionDto findById(Long id);

    List<SubscriptionDto> findAll(int page, int size, String sortDir, String sort);
}