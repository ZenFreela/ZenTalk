package com.zenfreela.zentalk;

import com.zenfreela.zentalk.model.Talk;
import com.zenfreela.zentalk.service.talk.TalkServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TalkServiceImplTest {

    @Autowired
    private TalkServiceImpl talkService;

    @Test
    public void register() {
        Talk talk = new Talk();
        talk.setMembers(Lists.newArrayList("jailsondev70@gmail.com"));
        talk.setMessages(null);

        Mono<Talk> talkMono = talkService.save(talk);
        Talk talkSave = talkMono.block();

        assertThat(talkSave, notNullValue());
    }

    @Test
    public void findByEmail() {
        String email = "jailsondev70@gmail.com";

        Flux<Talk> talkFlux = talkService.findByMembersIn(email);
        List<Talk> talkList = talkFlux.collectList().block();

        assertThat(talkList, notNullValue());
    }

}