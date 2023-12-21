package ru.rinat.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rinat.users.entity.UserEntity;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final UserJpaRepository userJpaRepository;
    @Transactional
    public Set<UserEntity> getUserEntityWithSubscriptions(UserEntity user) {
        return user.getSubscribedUsers();
    }
}
