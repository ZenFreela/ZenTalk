package com.zenfreela.zentalk.repository;

import com.zenfreela.zentalk.model.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    Flux<Message> findBySender(String sender);

}