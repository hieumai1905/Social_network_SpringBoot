package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.PostInteract;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.InteractType;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.postinteract.IPostInteractService;
import com.example.socialnetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/interact-post")
public class ApiInteractPostController {

    @Autowired
    private IPostInteractService postInteractService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @PostMapping("/{interact-type}")
    public ResponseEntity<?> interactPost(@PathVariable("interact-type") String interactionType, @RequestParam String postId,  @RequestParam String content) {
        InteractType interactType = InteractType.valueOf(interactionType.toUpperCase());
        Optional<User> user = userService.findById("60d5a805-81a1-43fa-a2cd-d86a615e933a");
        Optional<Post> post = postService.findById(postId);
        if (post.isPresent()) {
            PostInteract postInteract = new PostInteract();
            postInteract.setContent(content);
            postInteract.setPost(post.get());
            postInteract.setType(interactType);
            postInteract.setUser(user.get());
            postInteractService.save(postInteract);
        }
        return ResponseEntity.ok("success");
    }
}
