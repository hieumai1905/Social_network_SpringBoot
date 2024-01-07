package com.example.socialnetwork.services.comment;

import com.example.socialnetwork.models.Comment;
import com.example.socialnetwork.repositories.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Optional<Comment> save(Comment object) {
        return Optional.of(commentRepository.save(object));
    }

    @Override
    public boolean delete(Comment object) {
        try {
            commentRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Comment> findById(Long key) {
        return commentRepository.findById(key);
    }

    @Override
    public List<Comment> findAllByPostId(String postId) {
        return commentRepository.findAllByPost_PostId(postId);
    }
}
