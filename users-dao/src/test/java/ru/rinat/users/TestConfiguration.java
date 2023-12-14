package ru.rinat.users;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableAutoConfiguration(exclude =  {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = {"ru.rinat.users"})
//@EntityScan({.class})
public class TestConfiguration {
}
