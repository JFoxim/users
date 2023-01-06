package com.skillbox.users.repository;

import com.skillbox.users.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByDateTimeDeletedIsNull();

    Optional<User> findByLogin(String login);

    User findByFirstNameAndLastName(String firstName, String lastName);
}
