package com.zenfreela.zentalk.service.message;

import com.zenfreela.zentalk.model.Message;
import com.zenfreela.zentalk.service.CrudService;
import reactor.core.publisher.Flux;

public interface MessageService extends CrudService<Message> {

    Flux<Message> findBySender(String sender);

}