package ru.rinat.users.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_contact")
public class UserContactEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", length = 36)
    private UUID id;

    @NonNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "messenger")
    private String messenger;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}
