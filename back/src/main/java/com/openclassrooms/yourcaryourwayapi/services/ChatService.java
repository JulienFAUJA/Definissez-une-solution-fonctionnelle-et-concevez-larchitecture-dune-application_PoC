package com.openclassrooms.yourcaryourwayapi.services;

import com.openclassrooms.yourcaryourwayapi.dto.QuestionDTO;
import com.openclassrooms.yourcaryourwayapi.dto.QuestionPostDTO;
import com.openclassrooms.yourcaryourwayapi.models.ChatModel;
import com.openclassrooms.yourcaryourwayapi.models.QuestionModel;
import com.openclassrooms.yourcaryourwayapi.models.UserModel;
import com.openclassrooms.yourcaryourwayapi.repositories.ChatRepository;
import com.openclassrooms.yourcaryourwayapi.repositories.QuestionRepository;
import com.openclassrooms.yourcaryourwayapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository utilisateurRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Integer getChatId(String userEmail){
        if(userEmail.contains("@yourcaryourway.com")){

            // Récupérer le dernier chat_id de la base de données
            ChatModel dernierChat = chatRepository.findTopByOrderByChatIdDesc()
                    .orElseThrow(() -> new RuntimeException("No chats found in the database"));
            System.out.println("getChatId: Service client détecté:"+userEmail+" "+dernierChat.getChatId());
            return dernierChat.getChatId();
        }


        // Récupérer l'utilisateur connecté par email
        UserModel utilisateur = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Integer utilisateurId = utilisateur.getId();
        // Récupérer la dernière question posée par l'utilisateur
        QuestionModel derniereQuestion = questionRepository.findTopByUtilisateurIdOrderByQuestionIdDesc(utilisateurId)
                .orElseGet(() -> {
                    // Créer un nouveau chat et une question par défaut si aucune question n'est trouvée
                    QuestionDTO newQuestion = createChat(userEmail);
                    return questionRepository.findById(newQuestion.getId()).orElse(null);
                });

        if (derniereQuestion == null) {
            throw new RuntimeException("Failed to create a new chat for the user");
        }

        // Récupérer le chat_id de cette question
        Integer chatId = derniereQuestion.getChat().getChatId();
        return chatId;
    }

    public List<QuestionDTO> getAllQuestionsForUser(String userEmail) {
        // Récupérer le chat_id de cette question
        Integer chatId = getChatId(userEmail);

        // Récupérer toutes les questions liées à ce chat_id
        List<QuestionModel> questions = questionRepository.findByChatChatId(chatId);

        // Mapper les questions récupérées en QuestionDTO
        return questions.stream()
                .map(question -> {
                    QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
                    questionDTO.setUtilisateurEmail(question.getUtilisateur().getEmail());
                    questionDTO.setChatId(question.getChat().getChatId());
                    return questionDTO;
                })
                .collect(Collectors.toList());
    }

    public QuestionDTO postQuestion(QuestionPostDTO questionPostDTO, String userEmail) {
        UserModel utilisateur = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email"));

        if (questionPostDTO.getQuestionTexte() == null) {
            throw new RuntimeException("Question text cannot be null");
        }
        Integer chatId;
        if (questionPostDTO.getChat_id() == null) {
            // Récupérer le chat_id de cette question
            chatId = getChatId(userEmail);
        }
        else {
            chatId = questionPostDTO.getChat_id();
        }
        QuestionDTO questionDTO = new QuestionDTO(questionPostDTO.getQuestionTexte(), userEmail);
        QuestionModel question = modelMapper.map(questionDTO, QuestionModel.class);
        question.setUtilisateur(utilisateur);
        question.setChat(chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found")));

        QuestionModel savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }

    public QuestionDTO createChat(String userEmail) {
        UserModel utilisateur = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email"));

        ChatModel newChat = new ChatModel(LocalDateTime.now());
        chatRepository.save(newChat);

        QuestionDTO questionDTO = new QuestionDTO(
                "Nouveau Chat demandé par l'utilisateur...",
                utilisateur.getEmail());
        questionDTO.setChatId(newChat.getChatId());

        QuestionModel question = modelMapper.map(questionDTO, QuestionModel.class);
        question.setUtilisateur(utilisateur);
        question.setChat(newChat);

        QuestionModel savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }
}
