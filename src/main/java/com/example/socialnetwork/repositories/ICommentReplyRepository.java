package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Comment;
import com.example.socialnetwork.models.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentReplyRepository extends JpaRepository<CommentReply, Long> {
    List<CommentReply> findAllByComment_CommentId(Long commentId);
}
