package ru.rinat.users.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
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

	@Column(name = "gender", nullable = false)
	private String gender;

	@OneToOne
	@JoinColumn(name = "contact_id")
	private UserContactEntity userContact;

	@Column(name = "dt_deleted")
	private LocalDateTime dateTimeDeleted;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "subscription",
			joinColumns = @JoinColumn(name = "creator_user_id"),
			inverseJoinColumns = @JoinColumn(name = "subscriber_user_id"))
	private Set<UserEntity> subscribedUsers;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity userEntity = (UserEntity) o;
		return id.equals(userEntity.id) && login.equals(userEntity.login) && Objects.equals(firstName, userEntity.firstName) && Objects.equals(lastName, userEntity.lastName) && Objects.equals(patronymic, userEntity.patronymic) && gender.equals(userEntity.gender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, firstName, lastName, patronymic, gender);
	}
}
