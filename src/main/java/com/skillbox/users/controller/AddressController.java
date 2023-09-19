package com.skillbox.users.controller;

import com.skillbox.users.dto.AddressDto;
import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.Address;
import com.skillbox.users.entity.User;
import com.skillbox.users.mapper.AddressMapper;
import com.skillbox.users.mapper.UserMapper;
import com.skillbox.users.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

	private final AddressService addressService;
	private final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@GetMapping
	public List<AddressDto> getAddreses() {
		log.info("Get list address...");
		List<Address> companyDtos = addressService.findAll();
		return companyDtos.stream().map(this::convertToDto).collect(Collectors.toList());
	}


	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Address address) {
		log.info(String.format("Create address with id %s", address.getId()));
		
		Address savedAddress = addressService.create(address);

		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAddress.getId()).toUri();

		return ResponseEntity.created(locationUri).build();
	}

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable UUID id) {
		return convertToDto(addressService.findById(id));
	}

	@PutMapping("/{id}")
	public String update(@RequestBody Address address, @PathVariable UUID id) {
		log.info(String.format("Update address with id %s", address.getId()));
		
		checkIdAddress(address, id);

		return addressService.update(address);
	}

	private void checkIdAddress(Address address, UUID id) {	
		if (address.getId() == null) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Не верно задан id для параметра Address");
		
		if (!address.getId().equals(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр Id не соотвествует Id параметра Address");
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@RequestBody Address address, @PathVariable UUID id) {
		log.info(String.format("Delete address with id %s", address.getId()));
		
		checkIdAddress(address, id);
		
		return addressService.delete(address);
	}


	private AddressDto convertToDto(Address address) {
		return addressMapper.toAddressDto(address);
	}

	private UserDto convertToDto(User user) {
		return userMapper.toUserDto(user);
	}
}
