package io.javabrains.spring_boot_config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("db")
@Data
public class DbSettings {
    private String connection;
    private String host;
    private int port;
}
