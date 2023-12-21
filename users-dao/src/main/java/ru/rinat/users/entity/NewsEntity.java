package ru.rinat.users.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class NewsEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "ID", length = 36)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserEntity userCreator;

    @Column(name = "subject")
    private String subject;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="dt_create")
    private LocalDateTime createDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity news = (NewsEntity) o;
        return Objects.equals(id, news.id)
                && Objects.equals(createDate, news.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate);
    }
}
