package ru.rinat.users;

import ru.rinat.users.contact.UserContactInfoEntity;
import ru.rinat.users.contact.UserContactType;
import ru.rinat.users.news.NewsEntity;
import ru.rinat.users.subscription.SubscriptionEntity;
import ru.rinat.users.userinfo.UserEntity;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

public final class TestData {

    public static final Long IVAN_CONTACT_ID = 1L;
    public static final Long IVAN_USER_ID = 1L;
    public static final Long KONSTANTIN_USER_ID = 1L;
    public static final Long SUBSCRIPTION_ID = 1L;
    public static final UUID NEWS_ONE_ID = UUID.fromString("95194280-c6f6-4117-897d-3ffa2b92761f");
    public static final UUID NEWS_SECOND_ID = UUID.fromString("f40ce831-6e87-4b1b-87bf-3710d1893458");

    public static UserEntity getIvanUserEntity() {
        return UserEntity.builder()
                .id(IVAN_USER_ID)
                .login("ivanLogin")
                .firstName("Ivan")
                .lastName("Ivanov")
                .patronymic("Ivanovich")
                .gender(Gender.MALE)
                .phone("79091234567")
                .email("ivan-test@mail.ru")
                .build();
    }

    public static UserEntity getKonstantinUserEntity() {
        return UserEntity.builder()
                .id(KONSTANTIN_USER_ID)
                .login("KonstantinLogin")
                .firstName("Konstantin")
                .patronymic("Konstantinovich")
                .lastName("Konstantinov")
                .gender(Gender.MALE)
                .phone("79444444444")
                .email("konstantin-test@mail.ru")
                .build();
    }

    public static UserContactInfoEntity getUserContactInfoEntity(UserEntity user) {
       return UserContactInfoEntity.builder()
                .id(IVAN_CONTACT_ID)
                .user(user)
                .userContactType(UserContactType.TELEGRAM)
                .value("@example")
                .build();
    }

    public static SubscriptionEntity getSubscription(UserEntity userCreator, UserEntity userSubscriber) {
        return SubscriptionEntity.builder()
                .id(SUBSCRIPTION_ID)
                .creatorUser(userCreator)
                .dateStart(ZonedDateTime.now())
                .subscriberUser(userSubscriber)
                .build();
    }

    public static Set<NewsEntity> getNews(UserEntity user) {
       NewsEntity newsOneEntity = NewsEntity.builder()
                .id(NEWS_ONE_ID)
                .userCreator(user)
                .createDateTime(ZonedDateTime.now())
                .subject("test news 2")
                .text("news 1")
                .build();

        NewsEntity newsSecondEntity = NewsEntity.builder()
                .id(NEWS_SECOND_ID)
                .userCreator(user)
                .createDateTime(ZonedDateTime.now())
                .subject("test news 2")
                .text("news 2")
                .build();
        return Set.of(newsOneEntity, newsSecondEntity);
    }
}
