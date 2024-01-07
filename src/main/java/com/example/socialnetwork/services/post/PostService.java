package com.example.socialnetwork.services.post;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.InteractType;
import com.example.socialnetwork.models.enums.PostType;
import com.example.socialnetwork.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public Optional<Post> save(Post object) {
        return Optional.of(postRepository.save(object));
    }

    @Override
    public boolean delete(Post object) {
        try {
            postRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Post> findById(String key) {
        return postRepository.findById(key);
    }

    @Override
    public List<Post> findAllPostForNewsFeed(String userId) {
        return postRepository.findAllPostForNewsFeed(userId);
    }

    @Override
    public List<Post> findPostForProfile(String userId) {
        return postRepository.findAllByUserForProfile(userId);
    }

    @Override
    public List<Post> findPostForProfileOfFriend(String userId) {
        return postRepository.findAllByUserForProfileOfFriend(userId);
    }

    @Override
    public List<Post> findAllPostByUserAndInteractType(String userId, String type) {
        List<Post> posts = postRepository.findAllByUser_UserIdAndByType(userId, type);
        return posts;
    }

    @Override
    public List<Post> findPostForProfileOfMe(String userId) {
        return postRepository.findAllByUserForProfileOfMe(userId);
    }

    @Override
    public List<Post> findAllByHashtagForUserId(String hashTag, String userId) {
        return postRepository.findAllByHashtagForUserId(hashTag, userId);
    }

    @Override
    public List<Post> findAllByUserId(String userId) {
        return postRepository.findAllByUser_UserIdOrderByCreateAtDesc(userId);
    }
}
