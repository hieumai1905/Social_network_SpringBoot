package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.models.key.ParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IParticipantRepository extends JpaRepository<Participant, ParticipantId> {
    @Query(value = "SELECT * FROM Participants p WHERE p.conversation_id = :conversationId", nativeQuery = true)
    List<Participant> getParticipantsByConversationId(Long conversationId);
}
