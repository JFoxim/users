package com.skillbox.users.repository;

import com.skillbox.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
       
	List<User> findByDateTimeDeletedIsNull();  	
	
 	Optional<User> findByLogin(String login);

	User findByFirstNameAndLastName(String firstName, String lastName);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "insert into users_scheme.subscription (creator_user_id, subscriber_user_id) VALUES (:creatorUserId, :subscriberUserId)", nativeQuery = true)
	void addSubscribe(@Param("creatorUserId") UUID creatorUserId, @Param("subscriberUserId") UUID subscriberUserId);
}