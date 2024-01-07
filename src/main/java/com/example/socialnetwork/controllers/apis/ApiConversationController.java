package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.ConversationRequestDTO;
import com.example.socialnetwork.DTOs.ConversationResponseDTO;
import com.example.socialnetwork.models.Conversation;
import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.ConversationType;
import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.services.conversation.IConversationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversations")
public class ApiConversationController {

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IUserService userService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping
    public ResponseEntity<?> getAllConversation() {
        List<Conversation> conversations = conversationService.getConversationJoinedByUserId(userId, ParticipantStatus.JOINED);
        List<ConversationResponseDTO> conversationResponseDTOs = ConvertUtils.convertList(conversations, ConversationResponseDTO.class);
        return ResponseEntity.ok(conversationResponseDTOs);
    }

    @PostMapping
    public ResponseEntity<?> createConversation(@RequestBody ConversationRequestDTO conversationRequestDTO) {
        User user = userService.findById(userId).get();
        List<String> participantIds = conversationRequestDTO.getParticipantIds();
        participantIds.add(userId);

        // lam sao de lay cuoc tro chuyen nay da ton tai hay chua
        // neu da ton tai thi tra ve cuoc tro chuyen do
        // neu chua ton tai thi tao moi
//        Conversation conversation = conversationService.getConversationByParticipantIds(participantIds);




        Conversation conversation = ConvertUtils.convert(conversationRequestDTO, Conversation.class);
        conversation.setUser(user);
        List<Participant> participants = new ArrayList<>();
        for (String participant : participantIds) {
            Optional<User> participantUser = userService.findById(participant);
            if (participantUser.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Participant participantAdd = new Participant(participantUser.get(), conversation, participantUser.get().getFullName());
            participants.add(participantAdd);
        }
        if (participants.size() > 1) {
            conversation.setType(ConversationType.GROUP);
        } else {
            conversation.setType(ConversationType.PERSONAL);
        }
        conversation.setParticipants(participants);
        conversationService.save(conversation);
        ConversationResponseDTO conversationResponseDTO = ConvertUtils.convert(conversation, ConversationResponseDTO.class);
        return ResponseEntity.ok(conversationResponseDTO);
    }
}
