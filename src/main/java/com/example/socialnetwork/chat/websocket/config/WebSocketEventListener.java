package com.example.socialnetwork.chat.websocket.config;

import com.example.socialnetwork.chat.websocket.ChatMessage;
import com.example.socialnetwork.models.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String senderId = (String) headerAccessor.getSessionAttributes().get("senderId");
        Long conversationId = (Long) headerAccessor.getSessionAttributes().get("conversationId");
        if (senderId != null) {
            log.info("UserId: {} LEAVE ConversationId: {}", senderId, conversationId);
//            var chatMessage = ChatMessage.builder()
//                    .type(MessageType.LEAVE)
//                    .senderId(senderId)
//                    .build();
//            messagingTemplate.convertAndSend("/conversation/" + chatMessage.getConversationId(), chatMessage);
        }
    }

}
