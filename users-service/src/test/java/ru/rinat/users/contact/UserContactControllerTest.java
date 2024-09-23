package ru.rinat.users.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.rinat.users.userinfo.UserDto;
import ru.rinat.users.userinfo.UserOperations;
import ru.rinat.users.userinfo.UserRequestMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.rinat.users.TestData.getContactRequest;
import static ru.rinat.users.TestData.getContactResponse;
import static ru.rinat.users.TestData.getUserContactDto;
import static ru.rinat.users.TestData.getUserDto;

@WebMvcTest(ContactController.class)
class UserContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserOperations userOperations;

    @MockBean
    private UserRequestMapper userRequestMapper;

    @MockBean
    private UserContactOperations userContactOperations;

    @MockBean
    private ContactRequestMapper contactRequestMapper;


    @Test
    @DisplayName("Create User Contact")
    void createTest() throws Exception {
        // given
        ContactRequest contactRequest = getContactRequest();
        UserDto userDto = getUserDto();
        UserContactDto userContactDto = getUserContactDto();

        // when
        when(userOperations.findById(eq(contactRequest.getUserId()))).thenReturn(userDto);
        when(contactRequestMapper.toDto(eq(contactRequest), eq(userDto))).thenReturn(userContactDto);
        when(userContactOperations.create(eq(userContactDto))).thenReturn(userContactDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .post("/contacts")
                        .content(asJsonString(contactRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    @DisplayName("Update User Contact")
    void updateTest() throws Exception {
        // given
        ContactRequest contactRequest = getContactRequest();
        UserDto userDto = getUserDto();

        UserContactDto userContactDto = getUserContactDto();

        // when
        when(contactRequestMapper.toDto(eq(contactRequest), eq(userDto))).thenReturn(userContactDto);
        when(userContactOperations.update(eq(userContactDto))).thenReturn(userContactDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .put("/contacts/1")
                        .content(asJsonString(contactRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    @DisplayName("Delete User Contact")
    void deleteTest() throws Exception {
        doNothing().when(userContactOperations).deleteById(any(Long.class));

        mvc.perform(MockMvcRequestBuilders
                        .delete("/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }

    @Test
    void findById() throws Exception {
        // given
        ContactResponse contactResponse = getContactResponse();
        UserContactDto userContactDto = getUserContactDto();

        // when
        when(contactRequestMapper.toResponseDto(userContactDto)).thenReturn(contactResponse);
        when(userContactOperations.findById(any(Long.class))).thenReturn(userContactDto);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .get("/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.login").value("login"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.patronymic").value("patronymic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.phone").value("89223489238"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.dateTimeDeleted").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.value").value("г. Саратов, ул. Садовая 23, 55"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userContactType").value("ADDRESS"));
    }

    @Test
    void findAll() throws Exception {
        // given
        ContactResponse contactResponse = getContactResponse();
        UserContactDto userContactDto = getUserContactDto();
        List<UserContactDto> userContactDtoList = List.of(userContactDto);

        // when
        when(contactRequestMapper.toResponseDto(userContactDto)).thenReturn(contactResponse);
        when(userContactOperations.findByUserCreatorId(any(Long.class))).thenReturn(userContactDtoList);

        // then
        mvc.perform(MockMvcRequestBuilders
                        .get("/contacts/userid/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.login").value("login"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.patronymic").value("patronymic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.phone").value("89223489238"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].user.dateTimeDeleted").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].value").value("г. Саратов, ул. Садовая 23, 55"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].userContactType").value("ADDRESS"));
    }

    public static String asJsonString(final ContactRequest contactRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(contactRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}