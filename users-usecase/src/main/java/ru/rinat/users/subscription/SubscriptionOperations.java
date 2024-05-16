package ru.rinat.users.subscription;

import ru.rinat.users.userinfo.UserDto;

import java.util.List;

public interface SubscriptionOperations {

    SubscriptionDto create(SubscriptionDto userDto);

    SubscriptionDto update(SubscriptionDto userDto);

    void deleteById(Long id);

    List<SubscriptionDto> findByCreatorUser(UserDto userCreator);

    List<SubscriptionDto> findAll(int page, int size, String sortDir, String sort);
}
