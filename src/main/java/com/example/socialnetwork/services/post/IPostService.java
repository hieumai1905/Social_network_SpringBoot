package com.example.socialnetwork.services.post;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.enums.InteractType;
import com.example.socialnetwork.models.enums.PostType;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IPostService extends IGeneralService<Post, String> {
    List<Post> findAllPostForNewsFeed(String userId);

    List<Post> findPostForProfile(String userId);

    List<Post> findPostForProfileOfFriend(String userId);

    Optional<Post> findById(String key);

    List<Post> findAllPostByUserAndInteractType(String userId, String type);

    List<Post> findPostForProfileOfMe(String userId);

    List<Post> findAllByHashtagForUserId(String hashTag, String userId);

    List<Post> findAllByUserId(String userId);
}
