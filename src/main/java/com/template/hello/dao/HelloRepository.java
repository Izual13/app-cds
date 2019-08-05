package com.template.hello.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends ReactiveCrudRepository<Stub, String> {

}

