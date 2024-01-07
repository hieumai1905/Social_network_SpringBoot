package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DTOs.UserResponseDTO;
import com.example.socialnetwork.models.*;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.services.like.ILikeService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.relation.IRelationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IRelationService relationService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping(value = "/profile.me")
    public ModelAndView showProfile() {
        Optional<User> userCurrent = userService.findById(userId);
        if (userCurrent.isEmpty()) {
            return new ModelAndView("errors/404");
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        UserResponseDTO userResponseDTO = ConvertUtils.convert(userCurrent.get(), UserResponseDTO.class);

        List<Post> postOfUser = postService.findAllByUserId(userId);
        List<Media> medias = new ArrayList<>();
        for(Post post : postOfUser) {
            medias.addAll(post.getMedias());
            boolean isLiked = likeService.existsByPostIdAndUserId(post.getPostId(), userId);
            post.setLiked(isLiked);

            for(Comment comment: post.getComments()) {
                boolean isLikedComment = likeService.existsByCommentIdAndUserId(comment.getCommentId(), userId);
                comment.setLiked(isLikedComment);
                for(CommentReply replyComment: comment.getCommentReplies()) {
                    boolean isLikedReplyComment = likeService.existsByCommentReplyIdAndUserId(replyComment.getCommentReplyId(), userId);
                    replyComment.setLiked(isLikedReplyComment);
                }
            }
        }
        List<User> users = this.userService.findAllByRelationTypeOfUser("FRIEND", userId);
        modelAndView.addObject("number_of_friends", users.size());
        modelAndView.addObject("author", true);
        modelAndView.addObject("user_info", userResponseDTO);
        modelAndView.addObject("medias", medias);
        modelAndView.addObject("posts", postOfUser);
        return modelAndView;
    }

    @GetMapping(value = "/profile")
    public ModelAndView showProfile(@RequestParam("id") String id) {
        Optional<User> userCurrent = userService.findById(id);
        if (userCurrent.isEmpty()) {
            return new ModelAndView("errors/404");
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        List<Relation> relations = relationService.findByUserIdAndOtherTargetId(userId, id);
        modelAndView.addObject("isFollow", false);
        modelAndView.addObject("isFriend", false);
        if(!relations.isEmpty()) {
            for(Relation relation: relations){
                if(relation.getType() == RelationType.BLOCK){
                    return new ModelAndView("errors/404");
                }
                if(relation.getType() == RelationType.FOLLOW){
                    modelAndView.addObject("isFollow", true);
                }
                if(relation.getType() == RelationType.FRIEND){
                    modelAndView.addObject("isFriend", true);
                }
            }
        }

        UserResponseDTO userResponseDTO = ConvertUtils.convert(userCurrent.get(), UserResponseDTO.class);

        List<Post> postOfUser = postService.findAllByUserId(id);
        List<Media> medias = new ArrayList<>();
        for(Post post : postOfUser) {
            medias.addAll(post.getMedias());
            boolean isLiked = likeService.existsByPostIdAndUserId(post.getPostId(), id);
            post.setLiked(isLiked);

            for(Comment comment: post.getComments()) {
                boolean isLikedComment = likeService.existsByCommentIdAndUserId(comment.getCommentId(), id);
                comment.setLiked(isLikedComment);
                for(CommentReply replyComment: comment.getCommentReplies()) {
                    boolean isLikedReplyComment = likeService.existsByCommentReplyIdAndUserId(replyComment.getCommentReplyId(), id);
                    replyComment.setLiked(isLikedReplyComment);
                }
            }
        }
        List<User> users = this.userService.findAllByRelationTypeOfUser("FRIEND", id);
        modelAndView.addObject("author", false);
        modelAndView.addObject("number_of_friends", users.size());
        modelAndView.addObject("user_info", userResponseDTO);
        modelAndView.addObject("medias", medias);
        modelAndView.addObject("posts", postOfUser);
        return modelAndView;
    }
}
