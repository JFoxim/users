package ru.rinat.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rinat.users.entity.UserContactEntity;

import java.util.UUID;

@Repository
interface NewsEntityJpaRepository extends JpaRepository<UserContactEntity, UUID> {
}
