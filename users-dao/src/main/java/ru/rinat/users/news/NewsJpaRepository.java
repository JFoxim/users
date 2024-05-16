package ru.rinat.users.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rinat.users.userinfo.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsJpaRepository extends JpaRepository<NewsEntity, UUID> {

    @Query("SELECT n FROM NewsEntity n WHERE n.userCreator.id = :userCreatorId")
    List<NewsEntity> findByUserCreatorId(@Param("userCreatorId") Long userCreatorId);

    List<NewsEntity> findByUserCreator(@Param("userCreator") UserEntity userCreator);
}
