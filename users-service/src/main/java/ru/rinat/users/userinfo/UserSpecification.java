package ru.rinat.users.userinfo;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSpecification {

    ResponseEntity<Object> create(UserRequest userRequest);

    void update(UserRequest userRequest, Long id);

    void delete(Long id);

    void markDeleted(Long id);

    UserResponse findById(Long id);

    UserResponse findByLogin(String login);

    List<UserResponse> findAll(int page, int size, String sortDir, String sort);
}
