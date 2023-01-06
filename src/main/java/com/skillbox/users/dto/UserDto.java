package com.skillbox.users.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class UserDto implements Serializable {

	private static final long serialVersionUID = -345819662589854161L;
	
	private UUID id;
	private String firstName;
	private String lastName;
	private LocalDateTime dateTimeDeleted;
	private String login;

	public UserDto() {
	}

	public UserDto(String firstName, String lastName, String login) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTimeDeleted, id, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(dateTimeDeleted, other.dateTimeDeleted) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login);
	}
}
