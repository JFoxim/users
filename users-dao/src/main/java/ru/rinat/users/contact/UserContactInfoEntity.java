package ru.rinat.users.contact;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import ru.rinat.users.userinfo.UserEntity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_contact_info")
public class UserContactInfoEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_contact_info_seq_generator")
    @SequenceGenerator(name = "user_contact_info_seq_generator",
            sequenceName = "user_contact_info_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NonNull
    @Column(name = "value")
    private String value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserContactType userContactType;
}
