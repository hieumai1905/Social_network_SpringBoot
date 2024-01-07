package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.MessageRequestDTO;
import com.example.socialnetwork.DTOs.MessageResponseDTO;
import com.example.socialnetwork.models.Conversation;
import com.example.socialnetwork.models.Message;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.services.conversation.IConversationService;
import com.example.socialnetwork.services.message.IMessageService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class ApiMessageController {
    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IUserService userService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @Autowired
    private IMessageService messageService;

    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<?> getAllMessageByConversationId(@PathVariable("conversationId") Long conversationId) {
        List<Message> messages = messageService.findAllByConversationId(conversationId);
        List<MessageResponseDTO> messageResponseDTOs = ConvertUtils.convertList(messages, MessageResponseDTO.class);
        for (MessageResponseDTO messageResponseDTO : messageResponseDTOs) {
            if (messageResponseDTO.getSenderId().equals(userId)) {
                messageResponseDTO.setUserCurrent(true);
            }
        }
        return ResponseEntity.ok(messageResponseDTOs);
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        Conversation conversation = conversationService.findById(messageRequestDTO.getConversationId()).get();
        User userSender = userService.findById(userId).get();
        Message message = ConvertUtils.convert(messageRequestDTO, Message.class);
        message.setConversation(conversation);
        message.setSender(userSender);
        Optional<Message> messageAdd = messageService.save(message);
        MessageResponseDTO messageResponseDTO = ConvertUtils.convert(messageAdd.get(), MessageResponseDTO.class);
        messageResponseDTO.setUserCurrent(true);
        return ResponseEntity.ok(messageResponseDTO);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") Long messageId) {
        Optional<Message> message = messageService.findById(messageId);
        if (message.isEmpty()) {
            return ResponseEntity.status(404).body("Message not found");
        }
        String senderId = message.get().getSender().getUserId();
        if (!senderId.equals(userId)) {
            return ResponseEntity.status(401).body("Message not belong to user");
        }
        if (message.isPresent()) {
            messageService.delete(message.get());
            return ResponseEntity.ok("Delete message id: " + messageId + " success");
        }
        return ResponseEntity.ok("Has error when delete message id: " + messageId);
    }
}
