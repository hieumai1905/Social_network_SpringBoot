package com.example.socialnetwork.services.conversation;

import com.example.socialnetwork.models.Conversation;
import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IConversationService extends IGeneralService<Conversation, Long> {
    List<Conversation> getConversationJoinedByUserId(String userId, ParticipantStatus status);

    Optional<Conversation> findById(Long key);
}
