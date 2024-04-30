package ru.rinat.users.userinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "login", unique = true, nullable = false)
	private String login;
	
	@Column(name = "first_name", nullable = false)
    private String firstName;
	
	@Column(name = "last_name")
    private String lastName;

	@Column(name = "patronymic")
	private String patronymic;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name ="email", nullable = false, unique = true)
	private String email;

	@Column(name ="phone")
	private String phone;

	@Column(name = "dt_deleted")
	private ZonedDateTime dateTimeDeleted;
}
