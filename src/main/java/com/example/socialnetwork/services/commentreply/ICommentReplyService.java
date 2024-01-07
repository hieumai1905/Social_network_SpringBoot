package com.example.socialnetwork.services.commentreply;

import com.example.socialnetwork.models.CommentReply;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICommentReplyService extends IGeneralService<CommentReply, Long> {
    Optional<CommentReply> findById(Long commentReplyId);
    List<CommentReply> findAllByCommentId(Long commentId);
}
