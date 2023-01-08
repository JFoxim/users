package com.skillbox.users.entity;

import com.skillbox.users.dict.Gender;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", length = 36)
    private UUID id;

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
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "dt_deleted")
    private LocalDateTime dateTimeDeleted;

    @ManyToMany
    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(name = "creator_user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_user_id"))
    @ToString.Exclude
    private Set<User> subscribedUsers;

    public User(String login, String firstName, String lastName, Gender gender) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender.getName();
    }

    public User(String login, String firstName, String lastName, String gender) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender='" + gender + '\'' +
                ", dateTimeDeleted=" + dateTimeDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(patronymic, user.patronymic) && Objects.equals(gender, user.gender) && Objects.equals(dateTimeDeleted, user.dateTimeDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, firstName, lastName, patronymic, gender, dateTimeDeleted);
    }
}
