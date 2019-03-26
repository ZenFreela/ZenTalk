package com.zenfreela.zentalk.service.talk;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TalkServiceImpl implements TalkService {

    private TalkRepository talkRepository;

    @Autowired
    public TalkServiceImpl(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    @Override
    public Flux<Talk> findByMembersIn(String member) {
        return talkRepository.findByMembersIn(member);
    }

    @Override
    public Flux<Talk> findAll() {
        return talkRepository.findAll();
    }

    @Override
    public Mono<Talk> findById(String id) {
        return talkRepository.findById(id);
    }

    @Override
    public Mono<Talk> save(Talk body) {
        return talkRepository.save(body);
    }

    @Override
    public Mono<Void> delete(String id) {
        return talkRepository.deleteById(id);
    }

}