package com.template.hello.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DatabaseClient databaseClient(DataBaseProperties properties) {
        PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(properties.getHost())
                .database(properties.getDatabase())
                .username(properties.getUsername())
                .port(properties.getPort())
                .password(properties.getPassword()).build());
        return DatabaseClient.create(connectionFactory);
    }


    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
