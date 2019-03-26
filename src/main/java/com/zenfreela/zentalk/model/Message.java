package com.zenfreela.zentalk.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "talk_messages")
public class Message extends AbstractModel {

    private String sender;

    private String content;

}