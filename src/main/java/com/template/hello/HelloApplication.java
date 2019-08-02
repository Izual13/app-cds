package com.template.hello;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.Micronaut;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;

public class HelloApplication {


    @Client("https://cataas.com")
    public interface CatClient {
        @Get("/cat")
        Maybe<byte[]> getRandomCat();
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println("http://localhost:8080");
        Micronaut.run(HelloApplication.class);
        System.out.println(String.format("startup: %s ms", (System.nanoTime() - start) / 1_000_000));
    }


    @Controller("/api")
    @RequiredArgsConstructor
    public static class HelloController {
        private final CatClient catClient;

        @Get("/hello")
        public Maybe<String> hello() {
            return Maybe.create(s -> s.onSuccess("Ok!"));
        }

        @Get(value = "/cat", produces = "image/png")
        private Maybe<byte[]> getCat() {
            return catClient.getRandomCat();
        }
    }

}
