package ru.rinat.users;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rinat.users.entity.UserContactEntity;
import ru.rinat.users.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("DaoTest")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init-db.sql")
public class PostgresEmbeddedDaoTestingApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
            "postgres:14.5-alpine")
            .withDatabaseName("users")
            .withUsername("postgres")
            .withPassword("postgres").withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserContactJpaRepository userContactJpaRepository;

    @Test
    @Transactional
    public void contextLoads() {
        Long userId = 1L;

        UserContactEntity userContact = UserContactEntity.builder()
                .phone("79876543210")
                .email("lytic@mail.ru")
                .id(UUID.fromString("bb5e6f21-cc00-4e1e-bd49-63855da641a6"))
                .build();

        userContactJpaRepository.save(userContact);

       UserEntity userEntity = UserEntity.builder()
                .id(userId)
                .login("testLogin")
                .firstName("firstName")
                .lastName("lastName")
                .gender("м")
                .userContact(userContact)
                .build();

        userJpaRepository.save(userEntity);

        UserEntity user = userJpaRepository.findById(userId).orElseThrow();

        assertNotNull(user);
        assertEquals("testLogin", user.getLogin());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("м", user.getGender());
        assertEquals("lytic@mail.ru", user.getUserContact().getEmail());
    }

    @Test
    @Sql("/data-check.sql")
    public void subscriptionTest() {
        UserEntity userPeter = userJpaRepository.findById(13L).orElseThrow();
        UserEntity userFoma = userJpaRepository.findById(14L).orElseThrow();

        assertNotNull(userPeter);
        assertNotNull(userFoma);
        UserEntityService userEntityService = new UserEntityService(userJpaRepository);

        Set<UserEntity> subscribtionSet = userEntityService
                .getUserEntityWithSubscriptions(userPeter);

        assertEquals(1, subscribtionSet.size());
        assertTrue(subscribtionSet.contains(userFoma));

//        Set<UserEntity> subscribtion = new HashSet<>();
//        subscribtion.add(userFoma);
//        userPeter.setSubscribedUsers(subscribtion);
//        userJpaRepository.save(userPeter);

        assertEquals("peter", userPeter.getLogin());
        assertEquals("foma", userFoma.getLogin());
    }
}