package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.LikeRequestDTO;
import com.example.socialnetwork.DTOs.LikeResponseDTO;
import com.example.socialnetwork.models.*;
import com.example.socialnetwork.services.comment.ICommentService;
import com.example.socialnetwork.services.commentreply.ICommentReplyService;
import com.example.socialnetwork.services.like.ILikeService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/likes")
public class ApiLikeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ICommentReplyService commentReplyService;

    @PostMapping
    public ResponseEntity<?> likePost(@RequestBody LikeRequestDTO likeRequestDTO) {
        String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";
        Like like = new Like();
        Optional<User> user = userService.findById(userId);
        like.setUser(user.get());
        if (likeRequestDTO.getPostId() != null) {
            Optional<Like> likeExits = likeService.findAllByPostIdAndUserId(likeRequestDTO.getPostId(), userId);
            if (likeExits.isPresent()) {
                likeService.delete(likeExits.get());
                return ResponseEntity.ok("success");
            }
            Optional<Post> post = postService.findById(likeRequestDTO.getPostId());
            like.setPost(post.get());
        } else if (likeRequestDTO.getCommentId() != 0) {
            Optional<Like> likeExits = likeService.findAllByCommentIdAndUserId(likeRequestDTO.getCommentId(), userId);
            if (likeExits.isPresent()) {
                likeService.delete(likeExits.get());
                return ResponseEntity.ok("success");
            }
            Optional<Comment> comment = commentService.findById(likeRequestDTO.getCommentId());
            like.setComment(comment.get());
        } else if (likeRequestDTO.getCommentReplyId() != 0) {
            Optional<Like> likeExits = likeService.findAllByCommentReplyIdAndUserId(likeRequestDTO.getCommentReplyId(), userId);
            if (likeExits.isPresent()) {
                likeService.delete(likeExits.get());
                return ResponseEntity.ok("success");
            }
            Optional<CommentReply> commentReply = commentReplyService.findById(likeRequestDTO.getCommentReplyId());
            like.setCommentReply(commentReply.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PostId or CommentId or CommentReplyId is required");
        }
        Optional<Like> likeOptional = likeService.save(like);
        if (likeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
        LikeResponseDTO likeResponseDTO = ConvertUtils.convert(likeOptional.get(), LikeResponseDTO.class);
        if (likeOptional.get().getCommentReply() != null) {
            likeResponseDTO.setCommentReplyId(likeOptional.get().getCommentReply().getCommentReplyId());
        }
        return ResponseEntity.ok(likeResponseDTO);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllLikePost(@RequestParam("id") String postId) {
        List<Like> likes = likeService.findAllByPostId(postId);
        List<LikeResponseDTO> likeResponseDTOS = ConvertUtils.convertList(likes, LikeResponseDTO.class);
        return ResponseEntity.ok(likeResponseDTOS);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getAllLikeComment(@RequestParam("id") Long commentId) {
        List<Like> likes = likeService.findAllByCommentId(commentId);
        List<LikeResponseDTO> likeResponseDTOS = ConvertUtils.convertList(likes, LikeResponseDTO.class);
        return ResponseEntity.ok(likeResponseDTOS);
    }

    @GetMapping("/comments-reply")
    public ResponseEntity<?> getAllLikeCommentReply(@RequestParam("id") Long commentReplyId) {
        List<Like> likes = likeService.findAllByCommentReplyId(commentReplyId);
        List<LikeResponseDTO> likeResponseDTOS = ConvertUtils.convertList(likes, LikeResponseDTO.class);
        return ResponseEntity.ok(likeResponseDTOS);
    }

}
