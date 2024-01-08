package com.example.socialnetwork.services.comment;

import com.example.socialnetwork.models.Comment;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICommentService extends IGeneralService<Comment, Long> {
    Optional<Comment> findById(Long key);
    List<Comment> findAllByPostId(String postId);
}
