package com.example.socialnetwork.chat.websocket;

import com.example.socialnetwork.models.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private MessageType type;
    private String content;
    private String senderId;
    private Long conversationId;
}
