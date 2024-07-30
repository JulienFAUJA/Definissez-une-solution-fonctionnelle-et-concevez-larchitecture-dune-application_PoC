package com.openclassrooms.yourcaryourwayapi.repositories;

// QuestionRepository.java
import com.openclassrooms.yourcaryourwayapi.models.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {
    Optional<QuestionModel> findById(Integer questionId);
    List<QuestionModel> findByUtilisateurId(Integer utilisateurId);
    Optional<QuestionModel> findTopByUtilisateurIdOrderByQuestionIdDesc(Integer utilisateurId);
    List<QuestionModel> findByChatChatId(Integer chatId);
}