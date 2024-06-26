package com.example.socialnetwork.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private String commentAt;
    private String userId;
//    private String PostId;
    private List<CommentReplyResponseDTO> commentReplies;
}
