package com.example.socialnetwork.DTOs;

import com.example.socialnetwork.models.UserTag;
import com.example.socialnetwork.models.enums.PostAccess;
import com.example.socialnetwork.models.enums.PostType;
import com.example.socialnetwork.models.key.UserTagId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
    private String postContent;
    private PostAccess access;
    private PostType postType;
    private List<String> userTagIds;
}
