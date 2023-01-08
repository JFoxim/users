package com.skillbox.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NewsNotFoundExeption(String message) {
		super(message);
	}
}
