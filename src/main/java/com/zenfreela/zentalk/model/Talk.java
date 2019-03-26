package com.zenfreela.zentalk.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users_talks")
public class Talk extends AbstractModel {

    private List<String> members;

    private List<Message> messages;

}