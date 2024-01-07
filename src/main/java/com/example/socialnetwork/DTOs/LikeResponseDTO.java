package com.example.socialnetwork.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDTO {
    private String userId;
    private String postId;
    private Long commentId;
    private Long commentReplyId;
}
