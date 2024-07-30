package com.openclassrooms.yourcaryourwayapi.dto;

public class QuestionPostDTO {
    private String questionTexte;


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

    @Override
    public String toString() {
        return "QuestionPostDTO{" +
                "questionTexte='" + questionTexte + '\'' +
                '}';
    }
}
