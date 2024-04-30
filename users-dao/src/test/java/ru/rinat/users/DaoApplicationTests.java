package ru.rinat.users;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rinat.users.contact.UserContactInfoEntity;
import ru.rinat.users.contact.UserContactJpaRepository;
import ru.rinat.users.news.NewsEntity;
import ru.rinat.users.news.NewsJpaRepository;
import ru.rinat.users.subscription.SubscriptionEntity;
import ru.rinat.users.subscription.SubscriptionJpaRepository;
import ru.rinat.users.userinfo.UserEntity;
import ru.rinat.users.userinfo.UserJpaRepository;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.rinat.users.TestData.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("DaoTest")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init-db.sql")
public class DaoApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
            "postgres:14.5-alpine")
            .withDatabaseName("users")
            .withUsername("postgres")
            .withPassword("postgres")
            .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private SubscriptionJpaRepository subscriptionJpaRepository;

    @Autowired
    private UserContactJpaRepository userContactJpaRepository;

    @Autowired
    private NewsJpaRepository newsJpaRepository;

    @BeforeEach
    void init() {
        userJpaRepository.deleteAll();
        userContactJpaRepository.deleteAll();
        subscriptionJpaRepository.deleteAll();
        newsJpaRepository.deleteAll();
    }

    @Test
    public void saveUsersAndContactTest() {
        UserEntity ivanUserEntity = getIvanUserEntity();
        UserEntity ivanUserEntitySaved = userJpaRepository.save(ivanUserEntity);

        UserContactInfoEntity userContact = getUserContactInfoEntity(ivanUserEntitySaved);
        userContactJpaRepository.save(userContact);

        UserEntity user = userJpaRepository.findById(IVAN_USER_ID).orElseThrow();
        UserContactInfoEntity userContractSaved = userContactJpaRepository.findById(IVAN_CONTACT_ID).orElseThrow();

        assertNotNull(user);
        assertNotNull(userContractSaved);
        assertEquals(ivanUserEntity.getLogin(), user.getLogin());
        assertEquals(ivanUserEntity.getFirstName(), user.getFirstName());
        assertEquals(ivanUserEntity.getPatronymic(), user.getPatronymic());
        assertEquals(ivanUserEntity.getLastName(), user.getLastName());
        assertEquals(ivanUserEntity.getGender(), user.getGender());
        assertEquals(ivanUserEntity.getPhone(), user.getPhone());
        assertEquals(ivanUserEntity.getEmail(), user.getEmail());

        assertEquals(userContact.getValue(), userContractSaved.getValue());
        assertEquals(userContact.getId(), userContractSaved.getId());
        assertEquals(userContact.getType(), userContractSaved.getType());
        assertEquals(userContact.getUser().getId(), userContractSaved.getUser().getId());
    }

    @Test
    public void saveSubscriptionTest() {
        UserEntity ivanUserEntity = getIvanUserEntity();
        UserEntity kostUserEntity = getKonstantinUserEntity();

        UserEntity ivanUserEntitySaved = userJpaRepository.save(ivanUserEntity);
        UserEntity kostUserEntitySaved = userJpaRepository.save(kostUserEntity);

        UserContactInfoEntity userContact = getUserContactInfoEntity(ivanUserEntitySaved);
        userContactJpaRepository.save(userContact);

        SubscriptionEntity subscriptionEntity = getSubscription(ivanUserEntitySaved, kostUserEntitySaved);
        subscriptionJpaRepository.save(subscriptionEntity);

        UserEntity ivan = userJpaRepository.findById(ivanUserEntity.getId()).orElseThrow();

        SubscriptionEntity subscriptionEntitySaved = subscriptionJpaRepository.findById(SUBSCRIPTION_ID).orElseThrow();

        assertNotNull(subscriptionEntitySaved);
        assertEquals(subscriptionEntity.getCreatorUser().getId(), subscriptionEntitySaved.getCreatorUser().getId());
        assertEquals(subscriptionEntity.getDateStart().toInstant().truncatedTo(ChronoUnit.SECONDS),
                subscriptionEntitySaved.getDateStart().toInstant().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(subscriptionEntity.getId(), subscriptionEntitySaved.getId());
        assertEquals(subscriptionEntity.getSubscriberUser().getId(), subscriptionEntitySaved.getSubscriberUser().getId());
        assertEquals(subscriptionEntity.getDateEnd(), subscriptionEntitySaved.getDateEnd());
    }

    @Test
    @Transactional
    public void saveNewsTest() {
        UserEntity ivanUserEntity = getIvanUserEntity();
        UserEntity ivanUserEntitySaved = userJpaRepository.save(ivanUserEntity);

        Set<NewsEntity> newsEntityList = getNews(ivanUserEntitySaved);

        NewsEntity n = newsEntityList.stream().findFirst().orElseThrow();
        NewsEntity newsOne = newsJpaRepository.save(n);

        assertNotNull(newsOne);

        System.out.println(newsJpaRepository.findAll());
        UserEntity ivanUserEntityW = userJpaRepository.findById(ivanUserEntity.getId()).orElseThrow();
        System.out.println(newsJpaRepository.findByUserCreatorId(ivanUserEntity.getId()));
    }

    @Test
    @Transactional
    @Sql("/data-check.sql")
    public void subscriptionTest() {
        UserEntity userPeter = userJpaRepository.findById(13L).orElseThrow();
        UserEntity userFoma = userJpaRepository.findById(14L).orElseThrow();

        assertNotNull(userPeter);
        assertNotNull(userFoma);

        List<SubscriptionEntity> peterSubscribtions = subscriptionJpaRepository.findByUserCreatorId(userPeter.getId());
        List<SubscriptionEntity> fomeSubscribtions = subscriptionJpaRepository.findByUserCreatorId(userFoma.getId());

        assertEquals(1, peterSubscribtions.size());
        assertEquals(1, fomeSubscribtions.size());
        SubscriptionEntity mySubscriptionEntity = peterSubscribtions.stream().findFirst().orElseThrow();

        assertEquals(mySubscriptionEntity.getSubscriberUser(), userFoma);

        assertEquals("peter", userPeter.getLogin());
        assertEquals("foma", userFoma.getLogin());
    }


    @Test
    @Transactional
    @Sql("/data-check.sql")
    public void newsTest() {
        UserEntity peter = userJpaRepository.findById(13L).orElseThrow();
        UserEntity foma = userJpaRepository.findById(14L).orElseThrow();

        assertNotNull(peter);
        assertNotNull(foma);
        assertEquals("peter", peter.getLogin());
        assertEquals("foma", foma.getLogin());

        List<NewsEntity> peterNews = newsJpaRepository.findByUserCreatorId(peter.getId());
        List<NewsEntity> fomaNews = newsJpaRepository.findByUserCreatorId(foma.getId());

        assertNotNull(peterNews);
        assertNotNull(fomaNews);

        NewsEntity newsPeterEntity = peterNews.stream().findFirst().orElseThrow();
        NewsEntity newsFomaEntity = fomaNews.stream().findFirst().orElseThrow();

        assertEquals("0de9930e-63b4-49e3-bb03-21f83ab4b56f", newsPeterEntity.getId().toString());
        assertEquals("test subject", newsPeterEntity.getSubject());
        assertEquals("my news is good", newsPeterEntity.getText());
        assertEquals(13L, newsPeterEntity.getUserCreator().getId());
        ZonedDateTime newsDate = ZonedDateTime.parse("2024-01-25T00:00:00-00:00", ISO_OFFSET_DATE_TIME);
        assertEquals(newsDate, newsPeterEntity.getCreateDateTime());

        assertEquals("b3e2ac6d-1225-46b8-84d1-02301b4d131f", newsFomaEntity.getId().toString());
        assertEquals("test subject", newsFomaEntity.getSubject());
        assertEquals("test news", newsFomaEntity.getText());
        assertEquals(14L, newsFomaEntity.getUserCreator().getId());
        ZonedDateTime newsSecondDate = ZonedDateTime.parse("2024-01-24T00:00:00-00:00", ISO_OFFSET_DATE_TIME);
        assertEquals(newsSecondDate, newsFomaEntity.getCreateDateTime());
    }
}