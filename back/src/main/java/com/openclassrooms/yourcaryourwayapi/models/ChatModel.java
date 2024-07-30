package com.openclassrooms.yourcaryourwayapi.models;

// Chat.java
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Chat")
public class ChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;
    @Column(name = "chat_date")
    private LocalDateTime chatDate;

    // Getters and Setters

    public LocalDateTime getChatDate() {
        return chatDate;
    }

    public void setChatDate(LocalDateTime chatDate) {
        this.chatDate = chatDate;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public ChatModel(LocalDateTime chatDate) {
        this.chatDate = chatDate;
    }

    public ChatModel() {

    }
}

