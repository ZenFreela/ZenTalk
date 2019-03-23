package com.zenfreela.zentalk.controller;

import com.zenfreela.zentalk.model.Message;
import com.zenfreela.zentalk.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Message>> findAll() {
        return ok(messageRepository.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Flux<Message>> findBySender(@PathVariable("email") @NonNull String email) {
        return ok(messageRepository.findBySender(email));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Message>> save(@RequestBody @NonNull Message message) {
        return ok(messageRepository.save(message));
    }

}