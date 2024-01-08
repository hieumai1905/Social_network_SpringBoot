package com.example.socialnetwork.services.message;

import com.example.socialnetwork.models.Message;
import com.example.socialnetwork.repositories.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public Optional<Message> save(Message object) {
        return Optional.of(messageRepository.save(object));
    }

    @Override
    public boolean delete(Message object) {
        try {
            messageRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Message> findAllByConversationId(Long conversationId) {
        return messageRepository.findAllByConversation_ConversationIdOrderBySendAt(conversationId);
    }

    @Override
    public Optional<Message> findById(Long key) {
        return messageRepository.findById(key);
    }
}
