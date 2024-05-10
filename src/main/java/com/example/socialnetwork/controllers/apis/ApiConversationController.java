package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.ConversationRequestDTO;
import com.example.socialnetwork.DTOs.ConversationResponseDTO;
import com.example.socialnetwork.DTOs.ParticipantResponseDTO;
import com.example.socialnetwork.models.Conversation;
import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.ConversationType;
import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.services.conversation.IConversationService;
import com.example.socialnetwork.services.participant.IParticipantService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/conversations")
public class ApiConversationController {

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IParticipantService participantService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping
    public ResponseEntity<?> getAllConversation() {
        List<Conversation> conversations = conversationService.getConversationJoinedByUserId(userId, ParticipantStatus.JOINED);
        List<ConversationResponseDTO> conversationResponseDTOs = ConvertUtils.convertList(conversations, ConversationResponseDTO.class);
        for(ConversationResponseDTO conversationResponseDTO : conversationResponseDTOs){
            List<Participant> participants = participantService.getParticipantsByConversationId(conversationResponseDTO.getConversationId());
            List<ParticipantResponseDTO> participantResponseDTOS = ConvertUtils.convertList(participants, ParticipantResponseDTO.class);
            conversationResponseDTO.setParticipants(participantResponseDTOS);
            conversationResponseDTO.setUserCurrentId(userId);
        }
        return ResponseEntity.ok(conversationResponseDTOs);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createConversation(@RequestBody ConversationRequestDTO conversationRequestDTO) {
        User user = userService.findById(userId).get();
        List<String> participantIds = conversationRequestDTO.getParticipantIds();
        participantIds.add(userId);
        Optional<Conversation> conversationExits = conversationService.findConversationsByUserIds(participantIds);
        if(conversationExits.isPresent()){
            ConversationResponseDTO conversationResponseDTO = ConvertUtils.convert(conversationExits.get(), ConversationResponseDTO.class);
            conversationResponseDTO.setUserCurrentId(userId);
            List<Participant> participants = participantService.getParticipantsByConversationId(conversationResponseDTO.getConversationId());
            List<ParticipantResponseDTO> participantResponseDTOS = ConvertUtils.convertList(participants, ParticipantResponseDTO.class);
            conversationResponseDTO.setParticipants(participantResponseDTOS);
            return ResponseEntity.ok(conversationResponseDTO);
        }
        Conversation conversation = ConvertUtils.convert(conversationRequestDTO, Conversation.class);
        conversation.setUser(user);
        List<Participant> participants = new ArrayList<>();
        String nameConversation = "";
        for (String participant : participantIds) {
            Optional<User> participantUser = userService.findById(participant);
            if (participantUser.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Participant participantAdd = new Participant(participantUser.get(), conversation, participantUser.get().getFullName());
            participantAdd.setStatus(ParticipantStatus.JOINED);
            participants.add(participantAdd);
            nameConversation += participantUser.get().getFullName() + ", ";
        }
        if (participants.size() > 2) {
            conversation.setType(ConversationType.GROUP);
            nameConversation = nameConversation.substring(0, nameConversation.length() - 2);
        } else {
            conversation.setType(ConversationType.PERSONAL);
            nameConversation = "";
        }
        conversation.setName(nameConversation);
        conversation.setParticipants(participants);
        conversationService.save(conversation);
        ConversationResponseDTO conversationResponseDTO = ConvertUtils.convert(conversation, ConversationResponseDTO.class);
        conversationResponseDTO.setUserCurrentId(userId);
        return ResponseEntity.ok(conversationResponseDTO);
    }

    @GetMapping("/check/{userTargetId}")
    public ResponseEntity<Long> checkConversationPersonal(@PathVariable String userTargetId) {
        Conversation conversation = conversationService.getByUserIdAndUserTargetIdAndTypePersonal(userId, userTargetId);
        if (conversation != null) {
            return ResponseEntity.ok(conversation.getConversationId());
        }
        return ResponseEntity.ok(-1L);
    }
}
