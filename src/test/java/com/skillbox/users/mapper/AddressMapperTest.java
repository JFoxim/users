package com.skillbox.users.mapper;

import com.skillbox.users.dto.AddressDto;
import com.skillbox.users.entity.Address;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @Test
    void convertToAddressDto() {
        Address address = new Address();
        address.setId(UUID.fromString("49c0a419-117c-48fc-80f4-bd3ce1b44a40"));
        address.setCity("Москва");
        address.setStreet("Ленина");
        address.setFlatNumber(12);
        address.setHouseNumber("6");

        AddressDto addressDto = addressMapper.toAddressDto(address);

        assertEquals(address.getId(), addressDto.getId());
        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getStreet(), addressDto.getStreet());
        assertEquals(address.getFlatNumber(), addressDto.getFlatNumber());
        assertEquals(address.getHouseNumber(), addressDto.getHouseNumber());
    }

    @Test
    void convertToAddressEntity() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(UUID.fromString("49c0a419-117c-48fc-80f4-bd3ce1b44a40"));
        addressDto.setCity("Москва");
        addressDto.setStreet("Ленина");
        addressDto.setFlatNumber(12);
        addressDto.setHouseNumber("6");

        Address address = addressMapper.toAddressEntity(addressDto);

        assertEquals(addressDto.getId(), address.getId());
        assertEquals(addressDto.getCity(), address.getCity());
        assertEquals(addressDto.getStreet(), address.getStreet());
        assertEquals(addressDto.getFlatNumber(), address.getFlatNumber());
        assertEquals(addressDto.getHouseNumber(), address.getHouseNumber());
    }
}