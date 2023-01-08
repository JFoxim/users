package com.skillbox.users.controller;

import com.skillbox.users.dto.SubscribDto;
import com.skillbox.users.dto.UserDto;
import com.skillbox.users.entity.User;
import com.skillbox.users.mapper.UserMapper;
import com.skillbox.users.service.UserService;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
	
	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDto> getUsers() {
		logger.info("Get list users...");
		
		List<User> users = userService.findAll();
		return users.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody User user) {
		logger.info(String.format("Create user with id %s", user.getId()));
		
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
		logger.info(String.format("Update user with id %s", user.getId()));
		
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
		logger.info(String.format("Delete user with id %s", user.getId()));
		
		checkIdUser(user, id);
		
		return userService.delete(user);
	}

	@PostMapping("/addsubscribe")
	public String addSubscribe(@RequestBody SubscribDto subscribDto) {
		logger.info(String.format("Add subscribe  %s", subscribDto));
		return userService.addSubscrib(subscribDto);
	}

	private UserDto convertToDto(User user) {
		return UserMapper.INSTANCE.convert(user);
	}

}
