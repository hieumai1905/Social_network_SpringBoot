package com.example.socialnetwork.services.postinteract;

import com.example.socialnetwork.models.PostInteract;
import com.example.socialnetwork.models.enums.InteractType;
import com.example.socialnetwork.repositories.IPostInteractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostInteractService implements IPostInteractService {

    @Autowired
    private IPostInteractRepository postInteractRepository;

    @Override
    public Optional<PostInteract> save(PostInteract object) {
        return Optional.of(postInteractRepository.save(object));
    }

    @Override
    public boolean delete(PostInteract object) {
        try {
            postInteractRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
