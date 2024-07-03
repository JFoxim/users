package ru.rinat.users.userinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.rinat.users.TestData.getUserDto;
import static ru.rinat.users.TestData.getUserRequest;
import static ru.rinat.users.TestData.getUserResponse;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserOperations userOperations;

    @MockBean
    private UserRequestMapper userRequestMapper;


    @Test
    void createTest() throws Exception {
        // given
        UserRequest userRequest = getUserRequest();
        UserDto userDto = getUserDto();

        // when
        when(userRequestMapper.toDto(any(UserRequest.class))).thenReturn(userDto);
        when(userOperations.create(any(UserDto.class))).thenReturn(userDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(asJsonString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    void updateTest() throws Exception {
        UserRequest userRequest = getUserRequest();
        UserDto userDto = getUserDto();
        when(userRequestMapper.toDto(any(UserRequest.class), any(Long.class))).thenReturn(userDto);
        when(userOperations.update(any(UserDto.class))).thenReturn(userDto);

        mvc.perform(MockMvcRequestBuilders
                        .put("/users/1")
                        .content(asJsonString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(userOperations).deleteById(any(Long.class));

        mvc.perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    void markDeletedTest() throws Exception {
        doNothing().when(userOperations).markDeletedById(any(Long.class));

        mvc.perform(MockMvcRequestBuilders
                        .delete("/users/markdeleted/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    void findById() throws Exception {
        // given
        UserDto userDto = getUserDto();

        // when
        when(userRequestMapper.toResponseDto(any(UserDto.class))).thenReturn(getUserResponse());
        when(userOperations.findById(any(Long.class))).thenReturn(userDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("login"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.patronymic").value("patronymic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("89223489238"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.dateTimeDeleted").doesNotExist());;
    }

    @Test
    void findByLogin() throws Exception {
        // given
        UserDto userDto = getUserDto();

        // when
        when(userRequestMapper.toResponseDto(any(UserDto.class))).thenReturn(getUserResponse());
        when(userOperations.findByLogin(any(String.class))).thenReturn(userDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .get("/users/login/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.login").value("login"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.patronymic").value("patronymic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("89223489238"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.dateTimeDeleted").doesNotExist());
    }

    @Test
    void findAll() throws Exception {
        // given
        UserDto userDto = getUserDto();

        // when
        when(userRequestMapper.toResponseDto(any(UserDto.class))).thenReturn(getUserResponse());
        when(userOperations.findAll(any(Integer.class), any(Integer.class), any(String.class),
                any(String.class))).thenReturn(List.of(userDto));

        // then
        mvc.perform(MockMvcRequestBuilders
                        .get("/users/all/0/2/desc/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].login").value("login"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].patronymic").value("patronymic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].phone").value("89223489238"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].dateTimeDeleted").doesNotExist());
    }

    public static String asJsonString(final UserRequest userRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(userRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}