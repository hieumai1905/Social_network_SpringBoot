package com.example.socialnetwork.services.hashtag;

import com.example.socialnetwork.models.Hashtag;
import com.example.socialnetwork.repositories.IHashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HashtagService implements IHashtagService {
    @Autowired
    private IHashTagRepository hashTagRepository;

    @Override
    public Optional<Hashtag> save(Hashtag object) {
        return Optional.of(hashTagRepository.save(object));
    }

    @Override
    public Optional<Hashtag> findByHashtag(String hashtag) {
        return hashTagRepository.findByHashtag(hashtag);
    }
}
