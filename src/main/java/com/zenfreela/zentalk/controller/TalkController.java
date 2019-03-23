package com.zenfreela.zentalk.controller;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/talk")
public class TalkController {

    private TalkRepository talkRepository;

    @Autowired
    public TalkController(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
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

        List<Talk> memberTalk = talkList.stream()
                .filter($ -> $.getMembers().contains(email))
                .collect(Collectors.toCollection(LinkedList::new));

        Flux<Talk> talkFlux = Flux.fromArray(
                memberTalk.toArray(new Talk[0])
        );

        return ok(talkFlux);
    }

}