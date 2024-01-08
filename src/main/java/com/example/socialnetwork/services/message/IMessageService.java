package com.example.socialnetwork.services.message;

import com.example.socialnetwork.models.Message;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IMessageService extends IGeneralService<Message, Long> {
    List<Message> findAllByConversationId(Long conversationId);

    Optional<Message> findById(Long key);
}
