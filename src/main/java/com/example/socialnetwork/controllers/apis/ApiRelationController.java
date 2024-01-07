package com.example.socialnetwork.controllers.apis;


import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.services.relation.IRelationService;
import com.example.socialnetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/relations")
public class ApiRelationController {

    @Autowired
    private IRelationService relationService;

    @Autowired
    private IUserService userService;

    // send request friend or remove request
    @PostMapping("/request")
    public ResponseEntity<?> requestFriend(@RequestParam("id") String userTargetId) {
        Optional<User> userTarget = this.userService.findById(userTargetId);
        if (userTarget.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<Relation> relationExits = relationService.findByUserIdAndOtherTargetId("50d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId);
        if (relationExits.size() > 0) {
            relationService.removeAllByUserIdAndUserTargetId("50d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId);
            return ResponseEntity.ok("remove request success");
        }
        Optional<User> userCurrent = this.userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        Relation relationRequest = new Relation(RelationType.REQUEST, userCurrent.get(), userTarget.get());
        relationService.save(relationRequest);
        Relation relationFollow = new Relation(RelationType.FOLLOW, userCurrent.get(), userTarget.get());
        relationService.save(relationFollow);
        return ResponseEntity.ok("add friend success");
    }

    // accept friend or remove friend
    @PostMapping("/accept")
    public ResponseEntity<?> acceptFriend(@RequestParam("id") String userTargetId) {
        Optional<User> userTarget = this.userService.findById(userTargetId);
        if (userTarget.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        boolean isFriend = relationService.findByUserIdAndUserTargetIdAndType("60d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId, RelationType.FRIEND).isPresent();
        if (isFriend) {
            relationService.removeAllOfUserAndUserTarget("60d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId);
            return ResponseEntity.ok("remove friend success");
        }
        List<Relation> relationExits = relationService.findByUserIdAndOtherTargetId(userTargetId, "60d5a805-81a1-43fa-a2cd-d86a615e933a");
        if (relationExits.size() == 0) {
            return ResponseEntity.badRequest().body("Request not found");
        }
        relationService.removeAllByUserIdAndUserTargetId(userTargetId, "60d5a805-81a1-43fa-a2cd-d86a615e933a");
        Optional<User> userCurrent = this.userService.findById("60d5a805-81a1-43fa-a2cd-d86a615e933a");
        Relation relationFriendUserCurrent = new Relation(RelationType.FRIEND, userCurrent.get(), userTarget.get());
        relationService.save(relationFriendUserCurrent);
        Relation relationFriendUserTarget = new Relation(RelationType.FRIEND, userTarget.get(), userCurrent.get());
        relationService.save(relationFriendUserTarget);
        Relation relationFollowUserTarget = new Relation(RelationType.FOLLOW, userCurrent.get(), userTarget.get());
        relationService.save(relationFollowUserTarget);
        Relation relationFollowUserCurrent = new Relation(RelationType.FOLLOW, userTarget.get(), userCurrent.get());
        relationService.save(relationFollowUserCurrent);
        return ResponseEntity.ok("Accept success");
    }

    // block friend or unblock friend
    @PostMapping("/block")
    public ResponseEntity<?> blockFriend(@RequestParam("id") String userTargetId) {
        Optional<User> userTarget = this.userService.findById(userTargetId);
        if (userTarget.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Relation> isBlocked = relationService.findByUserIdAndUserTargetIdAndType("50d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId, RelationType.BLOCK);
        if (isBlocked.isPresent()) {
            relationService.delete(isBlocked.get());
            return ResponseEntity.ok("un block success");
        }
        relationService.removeAllOfUserAndUserTarget("50d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId);
        Optional<User> userCurrent = this.userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        Relation relation = new Relation( RelationType.BLOCK, userCurrent.get(), userTarget.get());
        relationService.save(relation);
        return ResponseEntity.ok("block success");
    }

    // follow friend or unfollow friend
    @PostMapping("/follow")
    public ResponseEntity<?> followFriend(@RequestParam("id") String userTargetId) {
        Optional<User> userTarget = this.userService.findById(userTargetId);
        if (userTarget.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<Relation> relations = relationService.findByUserIdAndOtherTargetId("50d5a805-81a1-43fa-a2cd-d86a615e933a", userTargetId);
        if (relations.size() > 0) {
            for (Relation relation : relations) {
                if (relation.getType().equals(RelationType.FOLLOW)) {
                    relationService.delete(relation);
                    return ResponseEntity.ok("un follow success");
                }
            }
        }
        Optional<User> userCurrent = this.userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        Relation relation = new Relation( RelationType.FOLLOW, userCurrent.get(), userTarget.get());
        relationService.save(relation);
        return ResponseEntity.ok("follow success");
    }

    // reject request add friend
    @DeleteMapping("/reject")
    public ResponseEntity<?> rejectRequest(@RequestParam("id") String userIdRequest) {
        Optional<User> userRequest = this.userService.findById(userIdRequest);
        if (userRequest.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<Relation> relations = this.relationService.findByUserIdAndOtherTargetId(userIdRequest, "50d5a805-81a1-43fa-a2cd-d86a615e933a");
        if (relations.size() > 0) {
            for (Relation relation : relations) {
                if (relation.getType().equals(RelationType.REQUEST)) {
                    relationService.delete(relation);
                    return ResponseEntity.ok("reject success");
                }
            }
        }
        return ResponseEntity.badRequest().body("reject fail");
    }
}
