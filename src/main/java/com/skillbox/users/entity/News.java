package com.skillbox.users.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User userCreator;

    @Column(name = "subject")
    private String subject;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="dt_create")
    private LocalDateTime createDate;

    public News(){}

    public News(User userCreator, String subject, String text, LocalDateTime createDate) {
        this.userCreator = userCreator;
        this.subject = subject;
        this.text = text;
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", userCreatorId=" + userCreator.getId() +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(userCreator, news.userCreator) && Objects.equals(subject, news.subject) && Objects.equals(text, news.text) && Objects.equals(createDate, news.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCreator, subject, text, createDate);
    }
}
