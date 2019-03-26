package com.zenfreela.zentalk.repository;

import com.zenfreela.zentalk.model.Talk;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TalkRepository extends ReactiveMongoRepository<Talk, String> {

    Flux<Talk> findByMembersIn(String member);

}