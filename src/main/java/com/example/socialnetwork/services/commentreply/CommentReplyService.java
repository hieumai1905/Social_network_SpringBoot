package com.example.socialnetwork.services.commentreply;

import com.example.socialnetwork.models.CommentReply;
import com.example.socialnetwork.repositories.ICommentReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentReplyService implements ICommentReplyService {
    @Autowired
    private ICommentReplyRepository commentReplyRepository;

    @Override
    public Optional<CommentReply> save(CommentReply object) {
        return Optional.of(commentReplyRepository.save(object));
    }

    @Override
    public boolean delete(CommentReply object) {
        try {
            commentReplyRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<CommentReply> findById(Long commentReplyId) {
        return commentReplyRepository.findById(commentReplyId);
    }

    @Override
    public List<CommentReply> findAllByCommentId(Long commentId) {
        return commentReplyRepository.findAllByComment_CommentId(commentId);
    }
}
