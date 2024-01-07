package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long>{
    List<Like> findAllByPost_PostId(String postId);

    List<Like> findAllByComment_CommentId(Long commentId);

    List<Like> findAllByCommentReply_CommentReplyId(Long commentReplyId);

    Optional<Like> findAllByPost_PostIdAndUser_UserId(String postId, String userId);

    Optional<Like> findAllByComment_CommentIdAndUser_UserId(Long commentId, String userId);

    Optional<Like> findAllByCommentReply_CommentReplyIdAndUser_UserId(Long commentReplyId, String userId);

    boolean existsByPost_PostIdAndUser_UserId(String postId, String userId);

    boolean existsByComment_CommentIdAndUser_UserId(Long commentId, String userId);

    boolean existsByCommentReply_CommentReplyIdAndUser_UserId(Long commentReplyId, String userId);
}
