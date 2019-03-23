package com.zenfreela.zentalk.controller;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

}