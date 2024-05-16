package ru.rinat.users.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rinat.users.userinfo.UserEntity;

import java.util.List;

@Repository
public interface UserContactJpaRepository extends JpaRepository<UserContactInfoEntity, Long> {
    @Query("SELECT c FROM UserContactInfoEntity c WHERE c.user.id = :userId")
    List<UserContactInfoEntity> findByUserCreatorId(@Param("userId") Long userId);

    List<UserContactInfoEntity> findByUser(@Param("user") UserEntity user);
}
