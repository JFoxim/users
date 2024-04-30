package ru.rinat.users.subscription;

import jakarta.persistence.*;
import lombok.*;
import ru.rinat.users.userinfo.UserEntity;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription")
public class SubscriptionEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "subscription_seq_generator")
    @SequenceGenerator(name = "subscription_seq_generator",
            sequenceName = "subscription_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserEntity creatorUser;

    @ManyToOne
    @JoinColumn(name = "subscriber_user_id", nullable = false)
    private UserEntity subscriberUser;

    @Column(name = "date_start", nullable = false)
    private ZonedDateTime dateStart;

    @Column(name = "date_end")
    private ZonedDateTime dateEnd;
}
