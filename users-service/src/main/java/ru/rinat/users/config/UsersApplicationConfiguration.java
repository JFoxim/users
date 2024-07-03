package ru.rinat.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.rinat.users.news.NewsDaoOperations;
import ru.rinat.users.news.NewsOperations;
import ru.rinat.users.news.NewsOperationsImpl;
import ru.rinat.users.subscription.SubscriptionDaoOperations;
import ru.rinat.users.subscription.SubscriptionOperations;
import ru.rinat.users.subscription.SubscriptionOperationsImpl;
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

    @Bean
    NewsOperations getNewsOperations(NewsDaoOperations newsDaoOperations) {
        return NewsOperationsImpl.builder()
                .newsDaoOperations(newsDaoOperations)
                .build();
    }

    @Bean
    SubscriptionOperations getSubscriptionOperations(SubscriptionDaoOperations subscriptionDaoOperations) {
        return SubscriptionOperationsImpl.builder()
                .subscriptionDaoOperations(subscriptionDaoOperations)
                .build();
    }

    @Bean
    ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
