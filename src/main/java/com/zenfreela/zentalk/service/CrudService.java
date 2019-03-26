package com.zenfreela.zentalk.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T> {

    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<T> save(T body);

    Mono<Void> delete(String id);

}