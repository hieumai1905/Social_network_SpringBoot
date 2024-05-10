package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.ParticipantResponseDTO;
import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.services.participant.IParticipantService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/participants")
public class ApiParticipantController {
    @Autowired
    private IParticipantService participantService;

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<?> getParticipantsByConversationId(@PathVariable Long conversationId) {
        List<Participant> participants = participantService.getParticipantsByConversationId(conversationId);
        List<ParticipantResponseDTO> participantResponseDTOS = ConvertUtils.convertList(participants, ParticipantResponseDTO.class);
        return ResponseEntity.ok(participantResponseDTOS);
    }
}
