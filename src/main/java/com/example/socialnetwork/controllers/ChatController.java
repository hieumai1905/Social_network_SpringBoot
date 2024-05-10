package com.example.socialnetwork.controllers;

import com.example.socialnetwork.chat.websocket.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ChatController {
    private final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @MessageMapping("/chat.sendMessage/{conversationId}")
    @SendTo("/conversation/{conversationId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable("conversationId") String conversationId) {
        chatMessage.setConversationId(conversationId);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{conversationId}")
    @SendTo("/conversation/{conversationId}")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable("conversationId") String conversationId) {
        log.info("UserId: {} JOINED ConversationId: {}", chatMessage.getSenderId(), conversationId);
        headerAccessor.getSessionAttributes().put("senderId", chatMessage.getSenderId());
        headerAccessor.getSessionAttributes().put("conversationId", conversationId);
    }

    @GetMapping("/chat")
    public String showViewTestMessage() {
        return "message";
    }
}