package com.zenfreela.zentalk.repository;

import com.zenfreela.zentalk.model.Talk;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TalkRepository extends ReactiveMongoRepository<Talk, String> {

    Flux<Talk> findByMembersIn(String member);

}