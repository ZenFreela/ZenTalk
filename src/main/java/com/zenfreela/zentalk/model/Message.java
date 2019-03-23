package com.zenfreela.zentalk.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Getter @Setter
@Document(collection = "talk_messages")
public class Message {

    @Id
    private String id;

    private String sender;

    private String content;

    @CreatedDate
    private Date createdDate;

}