package com.example.socialnetwork.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationRequestDTO {
    private String name;

    private List<String> participantIds;

}
