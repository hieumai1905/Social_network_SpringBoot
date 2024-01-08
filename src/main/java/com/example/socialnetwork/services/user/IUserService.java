package com.example.socialnetwork.services.user;

import com.example.socialnetwork.DTOs.UserLoginDTO;
import com.example.socialnetwork.DTOs.UserRegisterDTO;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.services.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User, String> ,UserDetailsService {
    Optional<User> findById(String key);

    Optional<User> loginUser(UserLoginDTO userLoginDTO);

    Boolean registerUser(UserRegisterDTO userRegisterDTO);

    Optional<User> findByEmail(String email);

    List<User> findAllByRelationTypeForUser(String type, String userId);

    List<User> findAllByRelationTypeOfUser(String type, String userId);

    List<User> findAllByName(String name);

    List<User> findAllByNameInFriends(String name, String userId);

    UserDetails loadUserById(String userId);
}
