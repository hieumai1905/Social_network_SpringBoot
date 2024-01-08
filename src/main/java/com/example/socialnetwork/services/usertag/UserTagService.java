package com.example.socialnetwork.services.usertag;

import com.example.socialnetwork.models.UserTag;
import com.example.socialnetwork.models.key.UserTagId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTagService implements IUserTagService {
    @Override
    public Optional<UserTag> save(UserTag object) {
        return Optional.empty();
    }

    @Override
    public boolean delete(UserTag object) {
        return false;
    }
}
