package com.skillbox.users.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
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

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "user_ID"),
            inverseJoinColumns = @JoinColumn(name = "addresses_ID"))
    private Set<Address> addressies;

    @Column(name = "dt_deleted")
    private LocalDateTime dateTimeDeleted;

    public User() { }

    public User(String firstName, String lastName, String login, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.gender = gender;
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
        User other = (User) obj;
        return Objects.equals(dateTimeDeleted, other.dateTimeDeleted) && Objects.equals(id, other.id)
                && Objects.equals(login, other.login);
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getDateTimeDeleted() {
        return dateTimeDeleted;
    }

    public void setDateTimeDeleted(LocalDateTime dateTimeDeleted) {
        this.dateTimeDeleted = dateTimeDeleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Address> getAddressies() {
        return addressies;
    }

    public void setAddressies(Set<Address> addressies) {
        this.addressies = addressies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateTimeDeleted=" + dateTimeDeleted +
                '}';
    }
}
