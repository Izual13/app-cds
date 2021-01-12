package com.template.hello;

import com.template.hello.dao.HelloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
class HelloApplicationTests {

    @Autowired
    private HelloRepository repository;

    @Test
    void contextLoads() {
        long count = repository.count();
        System.out.println(count);
    }

}
