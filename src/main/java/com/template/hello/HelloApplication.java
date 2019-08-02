package com.template.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class HelloApplication {


    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println("http://localhost:8080");
        new SpringApplicationBuilder(HelloApplication.class)
//                .logStartupInfo(false)
                .run(args);

        System.out.println(String.format("startup: %s ms", (System.nanoTime() - start) / 1_000_000));
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
        private Mono<byte[]> getCat() {
            return webClient.get().uri("https://cataas.com/cat").exchange()
                    .flatMap(response -> response.bodyToMono(ByteArrayResource.class))
                    .map(ByteArrayResource::getByteArray);
        }
    }

}
