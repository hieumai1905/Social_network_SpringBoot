package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.UserHobbies;
import com.example.socialnetwork.models.enums.Hobbies;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.services.userhobbies.IUserHobbiesServices;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hobbies")
public class ApiHobbiesController {
    @Autowired
    private IUserHobbiesServices userHobbiesServices;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllHobbies() {
        List<String> hobbies = new ArrayList<>();
        for (Hobbies h : Hobbies.values()) {
            hobbies.add(h.name());
        }
        return ResponseEntity.ok(hobbies);
    }

    @PostMapping
    public ResponseEntity<?> addHobbies(@RequestBody List<String> hobbies) {
        List<UserHobbies> hobbiesExits = userHobbiesServices.getAllByUserId("50d5a805-81a1-43fa-a2cd-d86a615e933a");
        for (UserHobbies userHobby : hobbiesExits) {
            userHobbiesServices.delete(userHobby);
        }
        User user = userService.findById("50d5a805-81a1-43fa-a2cd-d86a615e933a").get();
        for (String hobby : hobbies) {
            UserHobbies userHobby = new UserHobbies(Hobbies.valueOf(hobby), user);
            userHobbiesServices.save(userHobby);
        }
        return ResponseEntity.ok("Success");
    }
}
