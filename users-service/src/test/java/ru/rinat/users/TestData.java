package ru.rinat.users;

import ru.rinat.users.contact.ContactRequest;
import ru.rinat.users.contact.UserContactDto;
import ru.rinat.users.contact.UserContactType;
import ru.rinat.users.userinfo.UserDto;
import ru.rinat.users.userinfo.UserRequest;
import ru.rinat.users.userinfo.UserResponse;

import java.time.ZonedDateTime;

public final class TestData {

    private static final Long ID = 1L;

    public static UserRequest getUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@mail.ru");
        userRequest.setLogin("login");
        userRequest.setGender(Gender.MALE);
        userRequest.setFirstName("Ivan");
        userRequest.setPatronymic("Ivanovich");
        userRequest.setPhone("89223489238");
        userRequest.setLastName("Ivanov");
        userRequest.setDateTimeDeleted(ZonedDateTime.now());
        return userRequest;
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .id(ID)
                .firstName("firstName")
                .lastName("lastName")
                .patronymic("patronymic")
                .login("login")
                .gender(Gender.MALE)
                .phone("89223489238")
                .email("test@mail.ru")
                .build();
    }

    public static UserResponse getUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(ID);
        userResponse.setGender(Gender.MALE);
        userResponse.setLogin("login");
        userResponse.setEmail("test@mail.ru");
        userResponse.setPatronymic("patronymic");
        userResponse.setFirstName("firstName");
        userResponse.setLastName("lastName");
        userResponse.setPhone("89223489238");
        userResponse.setDateTimeDeleted(null);
        return userResponse;
    }

    public static ContactRequest getContactRequest() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setUserContactType("ADDRESS");
        contactRequest.setValue("г. Саратов, ул. Садовая, 23, 55");
        contactRequest.setUserId(ID);
        return contactRequest;
    }

    public static UserContactDto getUserContactDto() {
       return UserContactDto.builder()
                .user(getUserDto())
                .id(ID)
                .userContactType(UserContactType.ADDRESS)
                .value("г. Саратов, ул. Садовая, 23, 55")
                .build();
    }
}
