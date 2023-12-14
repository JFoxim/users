package ru.rinat.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rinat.users.entity.UserEntity;

@Repository
interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
