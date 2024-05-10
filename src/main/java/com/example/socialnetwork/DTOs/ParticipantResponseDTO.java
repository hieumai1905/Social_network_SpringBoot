package com.example.socialnetwork.DTOs;

import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.models.key.ParticipantId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantResponseDTO {
    private ParticipantId participantId;

    private ParticipantStatus status;

    private Date deletedAt;

    private Date joinedAt;

    private String nickname;
}
