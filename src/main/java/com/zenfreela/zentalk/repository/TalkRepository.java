package com.zenfreela.zentalk.repository;

import com.zenfreela.zentalk.model.Talk;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TalkRepository extends ReactiveMongoRepository<Talk, String> {}