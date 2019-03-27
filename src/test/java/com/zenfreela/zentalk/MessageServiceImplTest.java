package com.zenfreela.zentalk;

import com.zenfreela.zentalk.model.Message;
import com.zenfreela.zentalk.service.message.MessageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    public void findAll() {
        Flux<Message> messageFlux = messageService.findAll();
        List<Message> messageList = messageFlux.collectList().block();

        assertThat(messageList, notNullValue());
    }

    @Test
    public void register() {
        Message message = new Message();
        message.setSender("jailsondev70@gmail.com");
        message.setContent("Lorem ipsum.");

        Mono<Message> messageMono = messageService.save(message);
        Message messageSave = messageMono.block();

        assertThat(messageSave, notNullValue());
        assertThat(messageSave.getSender(), is(message.getSender()));
        assertThat(messageSave.getContent(), is(message.getContent()));
    }

    @Test
    public void findBySender() {
        String email = "jailsondev70@gmail.com";

        Flux<Message> messageFlux = messageService.findBySender(email);
        List<Message> messageList = messageFlux.collectList().block();

        assertThat(messageList, notNullValue());
        messageList.forEach(message -> assertThat(message.getSender(), is(email)));
    }

}