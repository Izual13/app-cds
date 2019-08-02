package com.template.hello.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("database")
public class DataBaseProperties {
    private String host;
    private String username;
    private String database;
    private String password;
    private int port;
}
