package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.PostResponseDTO;
import com.example.socialnetwork.DTOs.UserResponseDTO;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class ApiSearchController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping
    public ResponseEntity<?> search(@RequestParam("q") String query) {
        // neu phan tu dau tien la # thi tim kiem theo hashtag
        if (query.charAt(0) == '#') {
            query = query.substring(1);
            // search by hashtag
            List<Post> posts = postService.findAllByHashtagForUserId(query, userId);
            List<PostResponseDTO> postResponseDTOS = ConvertUtils.convertList(posts, PostResponseDTO.class);
            return ResponseEntity.ok(postResponseDTOS);
        }
        // search by name
        List<User> users = userService.findAllByName(query);
        List<UserResponseDTO> userResponseDTOS = ConvertUtils.convertList(users, UserResponseDTO.class);
        return ResponseEntity.ok(userResponseDTOS);
    }

    @GetMapping("/friends")
    public ResponseEntity<?> searchInFriends(@RequestParam("q") String query) {
        // search by name
        List<User> users = userService.findAllByNameInFriends(query, userId);
        List<UserResponseDTO> userResponseDTOS = ConvertUtils.convertList(users, UserResponseDTO.class);
        return ResponseEntity.ok(userResponseDTOS);
    }
}
