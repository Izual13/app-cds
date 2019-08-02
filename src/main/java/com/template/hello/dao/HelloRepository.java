package com.template.hello.dao;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Log4j2
public class HelloRepository {

    private final DatabaseClient client;

    public void save() {
        client.execute()
                .sql("CREATE TABLE IF NOT EXISTS stub (uuid VARCHAR(255) PRIMARY KEY)")
                .fetch();
        client.insert()
                .into(Stub.class)
                .using(new Stub(UUID.randomUUID()));
    }

    public Flux<Stub> get() {
        save();
        return client.select()
                .from(Stub.class)
                .fetch().all();

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Stub {

        Stub(UUID uuid) {
            this.uuid = uuid.toString();
        }

        private String uuid;
    }
}
