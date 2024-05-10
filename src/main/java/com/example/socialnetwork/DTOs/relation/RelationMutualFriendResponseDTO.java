package com.example.socialnetwork.DTOs.relation;

import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.RelationType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationMutualFriendResponseDTO {
    private Long relationId;
    private RelationType type;
    private Date setAt;
    private User user;
    private User userTarget;
    private Long mutualFriendCount;
}
