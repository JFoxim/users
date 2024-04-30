package ru.rinat.users.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Configuration
@PropertySource("classpath:database.properties")
@EnableConfigurationProperties(DatabaseProperties.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableJpaRepositories(basePackages = {"${base.jpa.package}"})
@EntityScan("${base.entity.package}")
public class DataSourceConfiguration {

    @Autowired
    private DatabaseProperties databaseProperties;

    @Autowired
    public Environment env;

    @Primary
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        log.info(databaseProperties.toString());
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(Driver.class.getName());
        Properties dataSourceProperties = new Properties();
        dataSourceProperties.setProperty("currentSchema", Optional.ofNullable(databaseProperties.getSchema())
                .filter(s -> !StringUtils.isEmpty(s))
                .orElseThrow(() -> new RuntimeException("Data base schema wasn't found")));
        dataSourceProperties.setProperty("ApplicationName",
                env.getProperty("spring.application.name") +
                        "_" + env.getProperty("spring.profiles.active"));
        if (databaseProperties.isSsl()) {
            dataSourceProperties.setProperty("ssl", "true");
            dataSourceProperties.setProperty("sslcert", databaseProperties.getCertificatePath());
            dataSourceProperties.setProperty("sslkey", databaseProperties.getCertificateKeyPath());
            dataSourceProperties.setProperty("sslpassword", databaseProperties.getCertificatePassword());
        }
        hikariConfig.setJdbcUrl(databaseProperties.getUrl());
        hikariConfig.setUsername(databaseProperties.getUser());
        hikariConfig.setPassword(databaseProperties.getPassword());
        hikariConfig.setMinimumIdle(databaseProperties.getMinConnectionPoolSize());
        hikariConfig.setMaximumPoolSize(databaseProperties.getMaxConnectionPoolSize());
        hikariConfig.setConnectionTimeout(databaseProperties.getConnectionTimeout());
        hikariConfig.setValidationTimeout(databaseProperties.getValidationTimeout());
        hikariConfig.setIdleTimeout(databaseProperties.getIdleTimeout());
        hikariConfig.setMaxLifetime(databaseProperties.getMaxLifetime());
        hikariConfig.setDataSourceProperties(dataSourceProperties);
        hikariConfig.setLeakDetectionThreshold(databaseProperties.getLeakDetectionThreshold());
        return new HikariDataSource(hikariConfig);
    }
}
