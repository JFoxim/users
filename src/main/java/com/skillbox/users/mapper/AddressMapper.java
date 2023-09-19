package com.skillbox.users.mapper;

import com.skillbox.users.dto.AddressDto;
import com.skillbox.users.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressMapper {

    //@Mapping(target="id", expression = "java(mapToBuyerInfoId(address.id))")
    AddressDto toAddressDto(Address address);
    Address toAddressEntity(AddressDto addressDto);
}
