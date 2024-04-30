package ru.rinat.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.rinat.users.userinfo.UserDaoOperations;
import ru.rinat.users.userinfo.UserOperations;
import ru.rinat.users.userinfo.UserOperationsImpl;

@Configuration
@ComponentScan({
        "ru.rinat.users"
})
public class UsersApplicationConfiguration {

    @Bean
    UserOperations getUserOperations(UserDaoOperations userDaoOperations) {
        return UserOperationsImpl.builder()
                .userDaoOperations(userDaoOperations)
                .build();
    }
}
