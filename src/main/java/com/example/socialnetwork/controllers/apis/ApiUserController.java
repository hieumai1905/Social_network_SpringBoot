package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.UserResponseDTO;
import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.services.relation.IRelationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class ApiUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/relations/me")
    // user_id la user hien tai trong bang relation
    public ResponseEntity<?> getAllByTypeOfUser(@RequestParam("type") String type) {
        List<User> users = this.userService.findAllByRelationTypeOfUser(type, "50d5a805-81a1-43fa-a2cd-d86a615e933a");
        List<UserResponseDTO> userReposeDos = ConvertUtils.convertList(users, UserResponseDTO.class);
        return ResponseEntity.ok(userReposeDos);
    }

    @GetMapping("/relations")
    public ResponseEntity<?> getAllByTypeForUser(@RequestParam("type") String type) {
        List<User> users = this.userService.findAllByRelationTypeForUser(type, "50d5a805-81a1-43fa-a2cd-d86a615e933a");
        List<UserResponseDTO> userReposeDos = ConvertUtils.convertList(users, UserResponseDTO.class);
        return ResponseEntity.ok(userReposeDos);
    }

    @GetMapping
    public ResponseEntity<?> getUserById(@RequestParam("id") String id) {
        Optional<User> user = this.userService.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        UserResponseDTO userResponseDTO = ConvertUtils.convert(user.get(), UserResponseDTO.class);
        return ResponseEntity.ok(userResponseDTO);
    }
}
