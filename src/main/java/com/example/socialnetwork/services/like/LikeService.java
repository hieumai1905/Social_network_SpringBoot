package com.example.socialnetwork.services.like;

import com.example.socialnetwork.models.Like;
import com.example.socialnetwork.repositories.ILikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Optional<Like> save(Like object) {
        return Optional.of(likeRepository.save(object));
    }

    @Override
    public boolean delete(Like object) {
        try {
            likeRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Like> findAllByPostId(String postId) {
        return likeRepository.findAllByPost_PostId(postId);
    }

    @Override
    public List<Like> findAllByCommentId(Long commentId) {
        return likeRepository.findAllByComment_CommentId(commentId);
    }

    @Override
    public List<Like> findAllByCommentReplyId(Long commentReplyId) {
        return likeRepository.findAllByCommentReply_CommentReplyId(commentReplyId);
    }

    @Override
    public Optional<Like> findAllByPostIdAndUserId(String postId, String userId) {
        return likeRepository.findAllByPost_PostIdAndUser_UserId(postId, userId);
    }

    @Override
    public Optional<Like> findAllByCommentIdAndUserId(Long commentId, String userId) {
        return likeRepository.findAllByComment_CommentIdAndUser_UserId(commentId, userId);
    }

    @Override
    public Optional<Like> findAllByCommentReplyIdAndUserId(Long commentReplyId, String userId) {
        return likeRepository.findAllByCommentReply_CommentReplyIdAndUser_UserId(commentReplyId, userId);
    }

    @Override
    public boolean existsByPostIdAndUserId(String postId, String userId) {
        return likeRepository.existsByPost_PostIdAndUser_UserId(postId, userId);
    }

    @Override
    public boolean existsByCommentIdAndUserId(Long commentId, String userId) {
        return likeRepository.existsByComment_CommentIdAndUser_UserId(commentId, userId);
    }

    @Override
    public boolean existsByCommentReplyIdAndUserId(Long commentReplyId, String userId) {
        return likeRepository.existsByCommentReply_CommentReplyIdAndUser_UserId(commentReplyId, userId);
    }
}
