package com.example.socialnetwork.models;

import com.example.socialnetwork.models.enums.ParticipantStatus;
import com.example.socialnetwork.models.key.ParticipantId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "participants")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    @EmbeddedId
    private ParticipantId participantId;

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ParticipantStatus status;

    @Column(name = "delete_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "joined_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    @MapsId("conversationId")
    private Conversation conversation;

    @PrePersist
    public void onCreate() {
        this.status = ParticipantStatus.JOINED;
        this.joinedAt = new Date();
    }

    public Participant(User user, Conversation conversation, String nickname) {
        this.user = user;
        this.conversation = conversation;
        this.nickname = nickname;
        this.participantId = new ParticipantId(user.getUserId(), conversation.getConversationId());
    }
}

