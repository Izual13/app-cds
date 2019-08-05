package com.template.hello.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(DataBaseProperties properties) {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(properties.getHost())
                .database(properties.getDatabase())
                .username(properties.getUsername())
                .port(properties.getPort())
                .password(properties.getPassword()).build());
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
