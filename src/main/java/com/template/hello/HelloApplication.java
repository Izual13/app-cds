package com.template.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.info.InfoContributorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.web.tomcat.TomcatMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.mappings.MappingsEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementContextAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@SpringBootApplication
public class HelloApplication {


    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        long x = System.nanoTime();
        System.out.println("http://localhost:8080");
        new SpringApplicationBuilder(HelloApplication.class)
                .logStartupInfo(false)
                .run(args);

        System.out.println(String.format("startup: %s seconds", (System.nanoTime() - x) / 1_000_000_000));
    }


    @RestController
    @RequestMapping("/api")
    @RequiredArgsConstructor
    public static class HelloController {

        private final WebClient webClient;

        @GetMapping("/hello")
        public Mono<String> hello() {
            return Mono.create(s -> s.success("Ok!"));
        }

        @GetMapping(value = "/cat", produces = MediaType.IMAGE_PNG_VALUE)
        private Mono<byte[]> getCat2() {
            return webClient.get().uri("https://cataas.com/cat").exchange()
                    .flatMap(response -> response.bodyToMono(ByteArrayResource.class))
                    .map(ByteArrayResource::getByteArray);
        }
    }

}
