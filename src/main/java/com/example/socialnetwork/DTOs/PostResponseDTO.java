package com.example.socialnetwork.DTOs;

import com.example.socialnetwork.models.enums.PostAccess;
import com.example.socialnetwork.models.enums.PostType;
import com.example.socialnetwork.models.key.UserTagId;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private String postId;
    private String postContent;
    private Date createAt;
    private PostAccess access;
    private PostType postType;
    private String userId;
    private List<UserTagId> userTags;
    private List<CommentResponseDTO> comments;
    private List<MediaResponseDTO> medias;
}
