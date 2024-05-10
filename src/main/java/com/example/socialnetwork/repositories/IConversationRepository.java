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

    @Query(value = "SELECT * FROM Conversations c " +
            "JOIN Participants p1 ON c.conversation_id = p1.conversation_id " +
            "JOIN Participants p2 ON c.conversation_id = p2.conversation_id " +
            "WHERE p1.user_id = :userId " +
            "AND p2.user_id = :userTargetId " +
            "AND c.type = 'PERSONAL'", nativeQuery = true)
    Conversation findByUserIdAndUserTargetIdAndTypePersonal(String userId, String userTargetId);

    @Query(value = "SELECT c.* FROM Conversations c " +
            "JOIN Participants p ON c.conversation_id = p.conversation_id " +
            "WHERE EXISTS (SELECT 1 FROM Participants p2 WHERE p2.conversation_id = c.conversation_id AND p2.user_id IN :userIds) " +
            "GROUP BY c.conversation_id " +
            "HAVING COUNT(DISTINCT p.user_id) = :#{#userIds.size()}", nativeQuery = true)
    Conversation findConversationsByUserIds(List<String> userIds);
}
