package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.PostRequestDTO;
import com.example.socialnetwork.DTOs.PostResponseDTO;
import com.example.socialnetwork.models.*;
import com.example.socialnetwork.models.enums.InteractType;
import com.example.socialnetwork.models.enums.MediaType;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.models.key.UserTagId;
import com.example.socialnetwork.services.media.IMediaService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.relation.IRelationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/posts")
public class ApiPostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMediaService mediaService;

    @Autowired
    private IRelationService relationService;

    @GetMapping
    public ResponseEntity<?> getPostById(@RequestParam("id") String id) {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        PostResponseDTO postResponseDTO = ConvertUtils.convert(post.get(), PostResponseDTO.class);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping(path = "/new-feed")
    public ResponseEntity<List<PostResponseDTO>> getAllPostForNewFeed() {
        List<Post> posts = postService.findAllPostForNewsFeed("60d5a805-81a1-43fa-a2cd-d86a615e933a");
        List<PostResponseDTO> postResponseDTOs = ConvertUtils.convertList(posts, PostResponseDTO.class);
        return ResponseEntity.ok(postResponseDTOs);
    }

    @GetMapping(path = "/hidden")
    public ResponseEntity<List<PostResponseDTO>> getAllPostHiddenForUser() {
        List<Post> posts = postService.findAllPostByUserAndInteractType("60d5a805-81a1-43fa-a2cd-d86a615e933a", String.valueOf(InteractType.HIDDEN));
        List<PostResponseDTO> postResponseDTOs = ConvertUtils.convertList(posts, PostResponseDTO.class);
        return ResponseEntity.ok(postResponseDTOs);
    }

    @GetMapping(path = "/saved")
    public ResponseEntity<List<PostResponseDTO>> getAllPostSavedForUser() {
        List<Post> posts = postService.findAllPostByUserAndInteractType("60d5a805-81a1-43fa-a2cd-d86a615e933a", String.valueOf(InteractType.SAVED));
        List<PostResponseDTO> postResponseDTOs = ConvertUtils.convertList(posts, PostResponseDTO.class);
        return ResponseEntity.ok(postResponseDTOs);
    }

    @GetMapping(path = "/profile/users")
    public ResponseEntity<?> getAllPostProfileForUser(@RequestParam("id") String userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Post> posts = null;
        if (userId.equals("50d5a805-81a1-43fa-a2cd-d86a615e933a")) {
            posts = postService.findPostForProfileOfMe(userId);
        } else {
            Optional<Relation> userCurrentBlock = relationService.findByUserIdAndUserTargetIdAndType("50d5a805-81a1-43fa-a2cd-d86a615e933a", userId, RelationType.BLOCK);
            Optional<Relation> blockUserCurrent = relationService.findByUserIdAndUserTargetIdAndType(userId, "50d5a805-81a1-43fa-a2cd-d86a615e933a", RelationType.BLOCK);
            if (userCurrentBlock.isPresent() || blockUserCurrent.isPresent()) {
                return ResponseEntity.status(404).body("No permission to view this profile");
            }
            Optional<Relation> isFriend = relationService.findByUserIdAndUserTargetIdAndType("50d5a805-81a1-43fa-a2cd-d86a615e933a", userId, RelationType.FRIEND);
            if (isFriend.isPresent()) {
                posts = postService.findPostForProfileOfFriend(userId);
            } else {
                posts = postService.findPostForProfile(userId);
            }
        }

        List<PostResponseDTO> postResponseDTOs = ConvertUtils.convertList(posts, PostResponseDTO.class);
        return ResponseEntity.ok(postResponseDTOs);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@ModelAttribute PostRequestDTO postRequestDTO, @RequestParam("files") Optional<MultipartFile[]> files) {
        Post post = ConvertUtils.convert(postRequestDTO, Post.class);
        post.setPostId(UUID.randomUUID().toString());
        User user = userService.findById("60d5a805-81a1-43fa-a2cd-d86a615e933a").get();
        post.setUser(user);

        List<UserTag> userTags = new ArrayList<>();
        if (postRequestDTO.getUserTagIds() != null) {
            for (String userId : postRequestDTO.getUserTagIds()) {
                Optional<User> userTagCurrent = userService.findById(userId);
                if (userTagCurrent.isPresent()) {
                    UserTag userTag = new UserTag();
                    UserTagId userTagId = new UserTagId(userId, post.getPostId());
                    userTag.setUserTagId(userTagId);
                    userTag.setPost(post);
                    userTag.setUser(userTagCurrent.get());
                    userTags.add(userTag);
                }
            }
        }
        post.setUserTags(userTags);


        if (files.isPresent()) {
            List<Media> medias = new ArrayList<>();
            List<Map<String, String>> mediaUploads = mediaService.uploadFiles(files.get());
            for (Map<String, String> media : mediaUploads) {
                Media mediaEntity = new Media();
                mediaEntity.setPost(post);
                mediaEntity.setUrl(media.get("url"));
                String type = media.get("format");
                int index = type.indexOf("/");
                type = type.substring(0, index);
                mediaEntity.setType(MediaType.valueOf(type));
                medias.add(mediaEntity);
            }
            post.setMedias(medias);
        }
        Optional<Post> postAdd = postService.save(post);
        if (postAdd.isPresent()) {
            PostResponseDTO postResponseDTO = ConvertUtils.convert(postAdd.get(), PostResponseDTO.class);
            return ResponseEntity.ok(postResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping({"/{postId}"})
    @Transactional
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable String postId, @ModelAttribute PostRequestDTO postRequestDTO, @RequestParam("files") Optional<MultipartFile[]> files) {
        Optional<Post> post = postService.findById(postId);
        if (post.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        post.get().setPostContent(postRequestDTO.getPostContent());
        post.get().setAccess(postRequestDTO.getAccess());
        post.get().setPostType(postRequestDTO.getPostType());
        Optional<Post> postUpdate = postService.save(post.get());
        if (postUpdate.isPresent()) {
            boolean isDeletedMedia = mediaService.deleteAllByPost(postId);
            if (files.isPresent() && isDeletedMedia) {
                List<Map<String, String>> medias = mediaService.uploadFiles(files.get());
                for (Map<String, String> media : medias) {
                    Media mediaEntity = new Media();
                    mediaEntity.setPost(postUpdate.get());
                    mediaEntity.setUrl(media.get("url"));
                    String type = media.get("format");
                    int index = type.indexOf("/");
                    type = type.substring(0, index);
                    mediaEntity.setType(MediaType.valueOf(type));
                    mediaService.save(mediaEntity);
                }
            }
            PostResponseDTO postResponseDTO = ConvertUtils.convert(postUpdate.get(), PostResponseDTO.class);
            return ResponseEntity.ok(postResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("postId") String postId) {
        Optional<Post> post = postService.findById(postId);
        if (!post.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        boolean isDeleted = postService.delete(post.get());
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
