package com.zenfreela.zentalk.controller;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.service.talk.TalkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/talk")
public class TalkController {

    private TalkServiceImpl talkService;

    @Autowired
    public TalkController(TalkServiceImpl talkService) {
        this.talkService = talkService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Talk>> findAll() {
        return ok(talkService.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Flux<Talk>> findByEmail(@PathVariable("email") @NonNull String email) {
        return ok(talkService.findByMembersIn(email));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Talk>> save(@RequestBody @NonNull Talk talk) {
        return ok(talkService.save(talk));
    }

}