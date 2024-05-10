package com.example.socialnetwork.services.conversation;

import com.example.socialnetwork.models.Conversation;
import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.repositories.IConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConversationService implements IConversationService {
    @Autowired
    private IConversationRepository conversationRepository;

    @Override
    public Optional<Conversation> save(Conversation object) {
        return Optional.of(conversationRepository.save(object));
    }

    @Override
    public boolean delete(Conversation object) {
        try {
            conversationRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Conversation> getConversationJoinedByUserId(String userId, ParticipantStatus status) {
        return conversationRepository.findByUserIdAndParticipantStatus(userId, String.valueOf(status));
    }

    @Override
    public Optional<Conversation> findById(Long key) {
        return conversationRepository.findById(key);
    }

    @Override
    public Conversation getByUserIdAndUserTargetIdAndTypePersonal(String userId, String userTargetId) {
        return conversationRepository.findByUserIdAndUserTargetIdAndTypePersonal(userId, userTargetId);
    }

    @Override
    public Optional<Conversation> findConversationsByUserIds(List<String> userIds) {
        return Optional.ofNullable(conversationRepository.findConversationsByUserIds(userIds));
    }
}
