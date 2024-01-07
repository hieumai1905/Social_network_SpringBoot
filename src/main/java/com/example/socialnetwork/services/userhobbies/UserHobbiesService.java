package com.example.socialnetwork.services.userhobbies;

import com.example.socialnetwork.models.enums.Hobbies;
import com.example.socialnetwork.repositories.IUserHobbiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.socialnetwork.models.UserHobbies;

import java.util.List;
import java.util.Optional;

@Service
public class UserHobbiesService implements IUserHobbiesServices {
    @Autowired
    private IUserHobbiesRepository userHobbiesRepository;

    @Override
    public Optional<UserHobbies> save(UserHobbies object) {
        return Optional.of(userHobbiesRepository.save(object));
    }

    @Override
    public boolean delete(UserHobbies object) {
        userHobbiesRepository.delete(object);
        return true;
    }

    @Override
    public List<UserHobbies> getAllByUserId(String userId) {
        return userHobbiesRepository.findAllByUser_UserId(userId);
    }
}
