package ru.rinat.users.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("ru.rinat.users.database")
public class DatabaseProperties {
    private String url;
    private String user;
    private String password;
    private boolean showSql;
    private boolean ssl;
    private String certificatePath;
    private String certificateKeyPath;
    private String certificatePassword;
    private int minConnectionPoolSize = 6;
    private int maxConnectionPoolSize = 15;
    private long connectionTimeout = 5000;
    private long validationTimeout = 3000;
    private long idleTimeout = 600000;
    private long maxLifetime = 1800000;
    private long leakDetectionThreshold = 5 * 1000;
    private String schema;
}
