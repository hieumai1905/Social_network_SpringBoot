package com.example.socialnetwork.services.media;

import com.example.socialnetwork.models.Media;
import com.example.socialnetwork.services.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IMediaService extends IGeneralService<Media, Long> {
    List<Map<String, String>> uploadFiles(MultipartFile[] files);

    Boolean deleteAllByPost(String postId);
}
