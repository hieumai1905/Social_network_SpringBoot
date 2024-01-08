package com.example.socialnetwork.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO {
    private Long messageId;

    private String content;

    private Date sendAt;

    private Long conversationId;

    private String senderId;

    private boolean isUserCurrent;
}
