package ru.rinat.users.userinfo;

import ru.rinat.users.common.CommonResponseDto;

import java.util.List;

public interface UserSpecification {

    CommonResponseDto<?> create(UserRequest userRequest);

    CommonResponseDto<?> update(UserRequest userRequest, Long id);

    CommonResponseDto<?> delete(Long id);

    CommonResponseDto<?> markDeleted(Long id);

    CommonResponseDto<UserResponse> findById(Long id);

    CommonResponseDto<UserResponse> findByLogin(String login);

    CommonResponseDto<List<UserResponse>> findAll(int page, int size, String sortDir, String sort);
}
