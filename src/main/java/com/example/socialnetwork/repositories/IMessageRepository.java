package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByConversation_ConversationIdOrderBySendAt(Long conversationId);
}
