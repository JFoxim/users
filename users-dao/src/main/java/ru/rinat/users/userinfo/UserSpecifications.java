package ru.rinat.users.userinfo;

import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public interface UserSpecifications {
    public static Specification<UserEntity> hasPhone(String phone) {
        return (root, query, cb) ->
                phone == null ? cb.conjunction() : cb.equal(root.get("phone"), phone);
    }

    public static Specification<UserEntity> hasNotDateTimeDeleted(ZonedDateTime dateTimeDeleted) {
        return (root, query, cb) ->
                dateTimeDeleted == null ? cb.conjunction() : cb.isNull(root.get("dateTimeDeleted"));
    }

    public static Specification<UserEntity> firstNameContains(String substring) {
        return (root, query, cb) ->
                (substring == null || substring.isBlank())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("firstName")), "%" + substring.toLowerCase() + "%");
    }
}
