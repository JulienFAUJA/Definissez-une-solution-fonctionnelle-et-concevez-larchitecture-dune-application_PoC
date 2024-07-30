package com.openclassrooms.yourcaryourwayapi.dto;

public class QuestionDTO {
    private Integer id;
    private String questionTexte;
    private String utilisateurEmail;
    private Integer chatId;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getUtilisateurEmail() {
        return utilisateurEmail;
    }

    public void setUtilisateurEmail(String utilisateurEmail) {
        this.utilisateurEmail = utilisateurEmail;
    }

    public String getQuestionTexte() {
        return questionTexte;
    }

    public void setQuestionTexte(String questionTexte) {
        this.questionTexte = questionTexte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionDTO() {

    }

    public QuestionDTO(String questionTexte, String utilisateurEmail) {
        this.questionTexte = questionTexte;
        this.utilisateurEmail = utilisateurEmail;
    }
}