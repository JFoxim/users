package com.skillbox.users.mapper;

import com.skillbox.users.dto.AddressDto;
import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.Address;
import com.skillbox.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDto convert(Address address);
}
