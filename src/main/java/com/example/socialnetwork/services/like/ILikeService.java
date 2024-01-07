package com.example.socialnetwork.services.like;

import com.example.socialnetwork.models.Like;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikeService extends IGeneralService<Like, Long> {
    List<Like> findAllByPostId(String postId);

    List<Like> findAllByCommentId(Long commentId);

    List<Like> findAllByCommentReplyId(Long commentReplyId);

    Optional<Like> findAllByPostIdAndUserId(String postId, String userId);

    Optional<Like> findAllByCommentIdAndUserId(Long commentId, String userId);

    Optional<Like> findAllByCommentReplyIdAndUserId(Long commentReplyId, String userId);

    boolean existsByPostIdAndUserId(String postId, String userId);

    boolean existsByCommentIdAndUserId(Long commentId, String userId);

    boolean existsByCommentReplyIdAndUserId(Long commentReplyId, String userId);
}
