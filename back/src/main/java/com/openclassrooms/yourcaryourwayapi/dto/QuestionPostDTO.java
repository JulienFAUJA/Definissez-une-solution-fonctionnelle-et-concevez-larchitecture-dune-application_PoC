package com.openclassrooms.yourcaryourwayapi.dto;

public class QuestionPostDTO {
    private String questionTexte;
    private Integer chat_id;

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public String getQuestionTexte() {
        return questionTexte;
    }

    public void setQuestionTexte(String questionTexte) {
        this.questionTexte = questionTexte;
    }

    public QuestionPostDTO() {

    }

    public QuestionPostDTO(String questionTexte) {
        this.questionTexte = questionTexte;
    }

    public QuestionPostDTO(String questionTexte, Integer chat_id) {
        this.questionTexte = questionTexte;
        this.chat_id=chat_id;

    }

    @Override
    public String toString() {
        return "QuestionPostDTO{" +
                "questionTexte='" + questionTexte + '\'' +
                '}';
    }
}
