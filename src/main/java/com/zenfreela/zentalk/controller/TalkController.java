package com.zenfreela.zentalk.controller;

import com.zenfreela.zentalk.model.Message;
import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.repository.MessageRepository;
import com.zenfreela.zentalk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/talk")
public class TalkController {

    private TalkRepository talkRepository;
    private MessageRepository messageRepository;

    @Autowired
    public TalkController(TalkRepository talkRepository,
                          MessageRepository messageRepository) {
        this.talkRepository = talkRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Talk>> findAll() {
        return ok(talkRepository.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Flux<Talk>> findByEmail(@PathVariable("email") @NonNull String email) {
        List<Talk> talkList = talkRepository.findAll().collectList().block();
        if (talkList == null) {
            return notFound().build();
        }

        if (talkList.size() == 0) {
            return noContent().build();
        }

        Flux<Talk> talkFlux = Flux.fromArray(
                talkList.stream()
                        .filter($ -> $.getMembers().contains(email))
                        .toArray(Talk[]::new)
        );

        return ok(talkFlux);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Talk>> save(@RequestBody @NonNull Talk talk) {
        return ok(talkRepository.save(talk));
    }

    @PatchMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Mono<Talk>> updateById(@PathVariable("id") @NonNull String id,
                                                 @RequestBody @NonNull Talk body) {

        Talk talk = talkRepository.findById(id).block();
        if (talk == null) {
            return notFound().build();
        }

        List<Message> talkMessages = talk.getMessages();
        List<Message> bodyMessages = body.getMessages();
        if (bodyMessages.size() != 0) {
            talkMessages.addAll(bodyMessages);
            bodyMessages.forEach(messageRepository::save);
        }

        return ok(talkRepository.save(talk));
    }

}