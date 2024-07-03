package ru.rinat.users.userinfo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.rinat.users.TestData.getUserDto;
import static ru.rinat.users.TestData.getUserRequest;


class UserRequestMapperTest {

    private static final Long ID = 1L;


    @Test
    void toDtoTest() {
        // given
        UserRequest userRequest = getUserRequest();

        UserRequestMapper requestMapper = new UserRequestMapperImpl();

        // when
        UserDto userDto = requestMapper.toDto(userRequest);

        // then
        assertThat(userDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringFields("isDeleted")
                .isEqualTo(userRequest);
    }

    @Test
    void toDtoWithIdTest() {
        // given
        UserRequest userRequest = getUserRequest();

        UserRequestMapper requestMapper = new UserRequestMapperImpl();

        // when
        UserDto userDto = requestMapper.toDto(userRequest, ID);

        // then
        assertEquals(ID, userDto.getId());
        assertThat(userDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringFields("isDeleted")
                .isEqualTo(userRequest);
    }

    @Test
    void Test() {
        // given
        UserDto userDto = getUserDto();
        UserRequestMapper requestMapper = new UserRequestMapperImpl();

        // when
        UserResponse userResponse = requestMapper.toResponseDto(userDto);

        // then
        assertThat(userDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringFields("isDeleted")
                .isEqualTo(userResponse);
    }
}
