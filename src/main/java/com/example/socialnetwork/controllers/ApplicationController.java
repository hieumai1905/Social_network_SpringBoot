package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DTOs.UserResponseDTO;
import com.example.socialnetwork.DTOs.relation.RelationMutualFriendResponseDTO;
import com.example.socialnetwork.models.*;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.services.like.ILikeService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.relation.IRelationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ApplicationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IRelationService relationService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {
        Optional<User> userCurrent = userService.findById(userId);
        if (userCurrent.isEmpty()) {
            return new ModelAndView("errors/404");
        }

        List<Relation> relationRequestFriends = relationService.findByUserTargetIdAndType(userId, RelationType.REQUEST);
        List<RelationMutualFriendResponseDTO> relationMutualFriendResponseDTOS = ConvertUtils.convertList(relationRequestFriends, RelationMutualFriendResponseDTO.class);
        for (RelationMutualFriendResponseDTO relationMutualFriendResponseDTO : relationMutualFriendResponseDTOS) {
            String userIdCurrent = relationMutualFriendResponseDTO.getUserTarget().getUserId();
            Long countMutualFriends = relationService.countMutualFriends(userId, userIdCurrent);
            relationMutualFriendResponseDTO.setMutualFriendCount(countMutualFriends);
        }

        List<Post> postNewFeed = postService.findAllPostForNewsFeed(userId);
        for(Post post : postNewFeed) {
            boolean isLiked = likeService.existsByPostIdAndUserId(post.getPostId(), userId);
            post.setLiked(isLiked);

            List<Comment> comments = post.getComments();
            Collections.sort(comments, Comparator.comparing(Comment::getCommentAt)); // Sắp xếp danh sách comment theo ngày

            for (Comment comment : comments) {
                boolean isLikedComment = likeService.existsByCommentIdAndUserId(comment.getCommentId(), userId);
                comment.setLiked(isLikedComment);

                List<CommentReply> replies = comment.getCommentReplies();
                Collections.sort(replies, Comparator.comparing(CommentReply::getReplyAt)); // Sắp xếp danh sách comment reply theo ngày

                for (CommentReply replyComment : replies) {
                    boolean isLikedReplyComment = likeService.existsByCommentReplyIdAndUserId(replyComment.getCommentReplyId(), userId);
                    replyComment.setLiked(isLikedReplyComment);
                }
            }
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user_info", userCurrent.get());
        modelAndView.addObject("userRelationRequest", relationMutualFriendResponseDTOS);
        modelAndView.addObject("posts", postNewFeed);
        return modelAndView;
    }

    @GetMapping(value = "/error")
    public String showError() {
        return "errors/server-error";
    }

    @GetMapping(value = "/404")
    public String showError404() {
        return "errors/404";
    }
}
