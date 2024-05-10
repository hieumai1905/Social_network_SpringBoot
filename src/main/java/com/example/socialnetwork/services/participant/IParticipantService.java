package com.example.socialnetwork.services.participant;

import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;

public interface IParticipantService extends IGeneralService<Participant, Long> {
    List<Participant> getParticipantsByConversationId(Long conversationId);

}
