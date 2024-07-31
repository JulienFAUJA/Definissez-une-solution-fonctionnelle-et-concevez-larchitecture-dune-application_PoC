package com.openclassrooms.yourcaryourwayapi.repositories;

import com.openclassrooms.yourcaryourwayapi.models.ChatModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Abonnement repository.
 */
public interface ChatRepository extends CrudRepository<ChatModel, Integer> {
    Optional<ChatModel> findTopByOrderByChatIdDesc();
}


