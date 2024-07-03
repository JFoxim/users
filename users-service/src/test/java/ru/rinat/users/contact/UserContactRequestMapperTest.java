package ru.rinat.users.contact;

import org.junit.jupiter.api.Test;
import ru.rinat.users.userinfo.UserDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.rinat.users.TestData.getContactRequest;
import static ru.rinat.users.TestData.getUserContactDto;
import static ru.rinat.users.TestData.getUserDto;


class UserContactRequestMapperTest {

    private static final Long USER_CONTACT_ID = 1L;


    @Test
    void toDtoTest() {
        // given
        ContactRequest contactRequest = getContactRequest();
        UserDto userDto = getUserDto();
        ContactRequestMapper contactRequestMapper = new ContactRequestMapperImpl();

        // when
        UserContactDto userContactDto = contactRequestMapper.toDto(contactRequest, userDto);

        // then
        assertEquals(contactRequest.getValue(), userContactDto.getValue());
        assertEquals(contactRequest.getUserId(), userContactDto.getUser().getId());
        assertEquals(contactRequest.getUserContactType(), userContactDto.getUserContactType().name());
    }

    @Test
    void toDtoWithIdTest() {
        // given
        ContactRequest contactRequest = getContactRequest();
        UserDto userDto = getUserDto();
        ContactRequestMapper contactRequestMapper = new ContactRequestMapperImpl();

        // when
        UserContactDto userContactDto = contactRequestMapper.toDto(contactRequest, USER_CONTACT_ID, userDto);

        // then
        assertEquals(USER_CONTACT_ID, userDto.getId());

        assertEquals(contactRequest.getValue(), userContactDto.getValue());
        assertEquals(contactRequest.getUserId(), userContactDto.getUser().getId());
        assertEquals(contactRequest.getUserContactType(), userContactDto.getUserContactType().name());
     }

    @Test
    void Test() {
        // given
        UserContactDto userContactDto = getUserContactDto();
        ContactRequestMapper contactRequestMapper = new ContactRequestMapperImpl();

        // when
        ContactResponse userContactResponse = contactRequestMapper.toResponseDto(userContactDto);

        // then
        assertEquals(userContactResponse.getValue(), userContactDto.getValue());
        assertEquals(userContactResponse.getUser().getId(), userContactDto.getUser().getId());
        assertEquals(userContactResponse.getUserContactType(), userContactDto.getUserContactType().name());
    }
}
