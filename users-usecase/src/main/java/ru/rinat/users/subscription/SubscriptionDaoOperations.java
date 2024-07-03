package ru.rinat.users.subscription;

import java.util.List;

public interface SubscriptionDaoOperations {

    SubscriptionDto create(SubscriptionDto subscriptionDto);

    SubscriptionDto update(SubscriptionDto subscriptionDto);

    void deleteById(Long id);

    SubscriptionDto findById(Long id);

    List<SubscriptionDto> findByCreatorUserId(Long creatorUserId);

    List<SubscriptionDto> findAll(int page, int size, String sortDir, String sort);
}
