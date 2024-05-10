package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.NotificationResponseDTO;
import com.example.socialnetwork.models.Notification;
import com.example.socialnetwork.services.notification.INotificationService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/notifications"})
@CrossOrigin("*")
public class ApiNotificationController {
    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<?>> getAllNotificationForUser() {
        List<Notification> notifications = notificationService.findAllOrderDescByUserId("6dc51c34-ba52-4451-8b68-e3f3bdc04b93");
        List<NotificationResponseDTO> notificationResponseDTOs = ConvertUtils.convertList(notifications, NotificationResponseDTO.class);
        return ResponseEntity.ok(notificationResponseDTOs);
    }

    @PutMapping("/seen")
    public ResponseEntity<?> updateNotification(@RequestParam("id") Long notificationId) {
        Notification notification = notificationService.findById(notificationId).get();
        if (!(notification.getUser().getUserId()).equals("50d5a805-81a1-43fa-a2cd-d86a615e933a")) {
            return ResponseEntity.status(401).body("You are not authorized to access this resource");
        }
        notification.setSeen(true);
        notificationService.save(notification);
        NotificationResponseDTO notificationResponseDTO = ConvertUtils.convert(notification, NotificationResponseDTO.class);
        return ResponseEntity.ok(notificationResponseDTO);
    }

//    @PostMapping
//    public ResponseEntity<?> createNotification(@PathVariable("userId") String userId, @RequestBody) {
//        Optional<User> user = userService.findById(userId);
//        if(!user.isPresent()) {
//            return ResponseEntity.badRequest().body("User not found");
//        }
//        Notification notification = new Notification();
//        notification.setUser(user.get());
//        notificationService.save(notification);
//        return ResponseEntity.ok(notification);
//    }
}
