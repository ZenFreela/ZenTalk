package com.zenfreela.zentalk.service.talk;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.service.CrudService;
import reactor.core.publisher.Flux;

public interface TalkService extends CrudService<Talk> {

    Flux<Talk> findByMembersIn(String member);

}