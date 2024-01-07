package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConversationRepository extends JpaRepository<Conversation, Long> {
    @Query(value = "SELECT * FROM conversations c JOIN participants p ON c.conversation_id = p.conversation_id WHERE p.user_id = :userId AND p.status = :status", nativeQuery = true)
    List<Conversation> findByUserIdAndParticipantStatus(String userId, String status);
}
