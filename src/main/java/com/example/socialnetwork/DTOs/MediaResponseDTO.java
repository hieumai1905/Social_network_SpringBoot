package com.example.socialnetwork.DTOs;

import com.example.socialnetwork.models.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaResponseDTO {
    private String url;
    private MediaType type;
//    private String postId;
}
