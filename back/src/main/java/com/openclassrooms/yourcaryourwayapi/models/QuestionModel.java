package com.openclassrooms.yourcaryourwayapi.models;

// QuestionModel.java
import jakarta.persistence.*;


@Entity
@Table(name = "Question")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "question_texte")
    private String questionTexte;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private UserModel utilisateur;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatModel chat;

    // Getters and Setters

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public ChatModel getChat() {
        return chat;
    }

    public void setChat(ChatModel chat) {
        this.chat = chat;
    }

    public UserModel getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UserModel utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getQuestionTexte() {
        return questionTexte;
    }

    public void setQuestionTexte(String questionTexte) {
        this.questionTexte = questionTexte;
    }
}