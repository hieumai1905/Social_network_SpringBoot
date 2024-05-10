package com.example.socialnetwork.services.user;

import com.example.socialnetwork.DTOs.UserLoginDTO;
import com.example.socialnetwork.DTOs.UserRegisterDTO;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.repositories.IUserRepository;
import com.example.socialnetwork.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> save(User object) {
        return Optional.of(userRepository.save(object));
    }

    @Override
    public boolean delete(User object) {
        try {
            userRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<User> findById(String key) {
        return userRepository.findById(key);
    }

    @Override
    public Optional<User> loginUser(UserLoginDTO userLoginDTO) {
        return Optional.ofNullable(userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
    }

    @Override
    public Boolean registerUser(UserRegisterDTO userRegisterDTO) {
        User user = ConvertUtils.convert(userRegisterDTO, User.class);
        user.setUserId(UUID.randomUUID().toString());
        Optional<User> userRegister = Optional.of(userRepository.save(user));
        if (userRegister.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public List<User> findAllByRelationTypeForUser(String type, String userId) {
        return userRepository.findAllByRelationTypeForUser(type, userId);
    }

    @Override
    public List<User> findAllByRelationTypeOfUser(String type, String userId) {
        return userRepository.findAllByRelationTypeOfUser(type, userId);
    }

    @Override
    public List<User> findAllByName(String name) {
        return userRepository.findAllByFullNameContaining(name);
    }

    @Override
    public List<User> findAllByNameInFriends(String name, String userId) {
        return userRepository.findAllByNameContainingInFriends(name, userId);
    }
}