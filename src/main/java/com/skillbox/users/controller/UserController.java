package com.skillbox.users.controller;

import com.skillbox.users.dto.SubscribDto;
import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.User;
import com.skillbox.users.mapper.UserMapper;
import com.skillbox.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@GetMapping
	public List<UserDto> getUsers(@RequestHeader Map<String, String> headers) {
		log.info("Get list users...");

		log.info(convertWithIteration(headers));

		List<User> users = userService.findAll();
		return users.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public String convertWithIteration(Map<String, String> map) {
		StringBuilder mapAsString = new StringBuilder("{");
		for (String key : map.keySet()) {
			mapAsString.append(key).append("=").append(map.get(key)).append(", ");
		}
		mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
		return mapAsString.toString();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody User user) {
		log.info(String.format("Create user with id %s", user.getId()));
		
		User savedUser = userService.create(user);

		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(locationUri).build();
	}

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable UUID id) {
		return convertToDto(userService.findById(id));
	}

	@PutMapping("/{id}")
	public String update(@RequestBody User user, @PathVariable UUID id) {
		log.info(String.format("Update user with id %s", user.getId()));
		
		checkIdUser(user, id);

		return userService.update(user);
	}

	private void checkIdUser(User user, UUID id) {
		if (user.getId() == null) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Не верно задан id для параметра User");
		
		if (!user.getId().equals(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр Id не соотвествует Id параметра User");
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@RequestBody User user, @PathVariable UUID id) {
		log.info(String.format("Delete user with id %s", user.getId()));
		
		checkIdUser(user, id);
		
		return userService.delete(user);
	}

	@PostMapping("/addsubscribe")
	public String addSubscribe(@RequestBody SubscribDto subscribDto) {
		log.info(String.format("Add subscribe  %s", subscribDto));
		return userService.addSubscrib(subscribDto);
	}

	private UserDto convertToDto(User user) {
		return userMapper.toUserDto(user);
	}

}
