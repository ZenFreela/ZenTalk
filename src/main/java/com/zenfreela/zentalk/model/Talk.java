package com.zenfreela.zentalk.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Getter @Setter
@Document(collection = "users_talks")
public class Talk {

    @Id
    private String id;

    private List<String> members;

    private List<Message> messages;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lasModifiedDate;

}