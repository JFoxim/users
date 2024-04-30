package ru.rinat.users.userinfo;

import ru.rinat.users.Gender;

import java.time.ZonedDateTime;

final class UserDomainEntity implements UserDomainModel {

    private final Long id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Gender gender;
    private final String email;
    private final String phone;
    private final ZonedDateTime dateTimeDeleted;

    public UserDomainEntity(Long id, String login, String firstName, String lastName, String patronymic,
                            Gender gender, String email, String phone, ZonedDateTime dateTimeDeleted) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dateTimeDeleted = dateTimeDeleted;
    }

    @Override
    public Boolean getIsDeleted() {
        return dateTimeDeleted == null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public ZonedDateTime getDateTimeDeleted() {
        return dateTimeDeleted;
    }
}
