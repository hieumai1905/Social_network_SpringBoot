package com.example.socialnetwork.services.hashtag;

import com.example.socialnetwork.models.Hashtag;
import com.example.socialnetwork.services.IGeneralService;

import java.util.Optional;

public interface IHashtagService {
    Optional<Hashtag> findByHashtag(String hashtag);

    Optional<Hashtag> save(Hashtag object);
}
