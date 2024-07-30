package com.openclassrooms.yourcaryourwayapi.controllers;

import com.openclassrooms.yourcaryourwayapi.dto.QuestionDTO;
import com.openclassrooms.yourcaryourwayapi.dto.QuestionPostDTO;
import com.openclassrooms.yourcaryourwayapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestionsForUser(Authentication authentication) {
        String userEmail = authentication.getName();
        return chatService.getAllQuestionsForUser(userEmail);
    }

    @PostMapping("/questions")
    public QuestionDTO postQuestion(@RequestBody QuestionPostDTO questionPostDTO, Authentication authentication) {
        String userEmail = authentication.getName();
        System.out.println("ChatController:"+questionPostDTO.toString()+ " "+userEmail);
        return chatService.postQuestion(questionPostDTO, userEmail);
    }

    @PostMapping("/create")
    public QuestionDTO createChat(@RequestParam String userEmail) {
        return chatService.createChat(userEmail);
    }
}
