package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.CommentReplyRequestDTO;
import com.example.socialnetwork.DTOs.CommentRequestDTO;
import com.example.socialnetwork.DTOs.CommentResponseDTO;
import com.example.socialnetwork.models.Comment;
import com.example.socialnetwork.models.CommentReply;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.services.comment.ICommentService;
import com.example.socialnetwork.services.commentreply.ICommentReplyService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class ApiCommentController {
    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ICommentReplyService commentReplyService;

    @Autowired
    private IUserService userService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getAllCommentByPostId(@PathVariable("postId") String postId) {
        List<Comment> comments = commentService.findAllByPostId(postId);
        List<CommentResponseDTO> commentResponseDTOs = ConvertUtils.convertList(comments, CommentResponseDTO.class);
        return ResponseEntity.ok(commentResponseDTOs);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        Comment newComment = ConvertUtils.convert(commentRequestDTO, Comment.class);
        Optional<Post> post = postService.findById(commentRequestDTO.getPostId());
        if (post.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        newComment.setPost(post.get());
        Optional<User> user = userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        newComment.setUser(user.get());
        Optional<Comment> commentAdded = commentService.save(newComment);
        if (commentAdded.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CommentResponseDTO commentResponseDTO = ConvertUtils.convert(commentAdded.get(), CommentResponseDTO.class);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PostMapping("/reply-cmt")
    public ResponseEntity<?> createCommentReply(@RequestBody CommentReplyRequestDTO commentReplyRequestDTO) {
        CommentReply newCommentReply = ConvertUtils.convert(commentReplyRequestDTO, CommentReply.class);
        Optional<Comment> comment = commentService.findById(commentReplyRequestDTO.getCommentId());
        if (comment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        newCommentReply.setComment(comment.get());
        Optional<User> user = userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        newCommentReply.setUser(user.get());
        Optional<CommentReply> commentReplyAdded = commentReplyService.save(newCommentReply);
        if (commentReplyAdded.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CommentResponseDTO commentResponseDTO = ConvertUtils.convert(commentReplyAdded.get(), CommentResponseDTO.class);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        Optional<Comment> comment = commentService.findById(commentId);
        if (comment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Comment newComment = ConvertUtils.convert(commentRequestDTO, Comment.class);
        newComment.setCommentId(commentId);
        Optional<Comment> commentUpdated = commentService.save(newComment);
        if (commentUpdated.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CommentResponseDTO commentResponseDTO = ConvertUtils.convert(commentUpdated.get(), CommentResponseDTO.class);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        Optional<Comment> comment = commentService.findById(commentId);
        if (comment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        commentService.delete(comment.get());
        return ResponseEntity.ok().build();
    }
}
