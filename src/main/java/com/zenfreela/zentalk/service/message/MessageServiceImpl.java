package com.zenfreela.zentalk.service.message;

import com.zenfreela.zentalk.model.Message;
import com.zenfreela.zentalk.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Flux<Message> findBySender(String sender) {
        return messageRepository.findBySender(sender);
    }

    @Override
    public Flux<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Mono<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public Mono<Message> save(Message body) {
        return messageRepository.save(body);
    }

    @Override
    public Mono<Void> delete(String id) {
        return messageRepository.deleteById(id);
    }
}
