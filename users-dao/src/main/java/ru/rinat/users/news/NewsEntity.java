package ru.rinat.users.news;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.rinat.users.userinfo.UserEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class NewsEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserEntity userCreator;

    @Column(name = "subject")
    private String subject;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="dt_create")
    private ZonedDateTime createDateTime;
}
