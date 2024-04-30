package ru.rinat.users.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query("SELECT s FROM SubscriptionEntity s WHERE s.creatorUser.id = :userCreatorId")
    List<SubscriptionEntity> findByUserCreatorId(@Param("userCreatorId") Long userCreatorId);
}
