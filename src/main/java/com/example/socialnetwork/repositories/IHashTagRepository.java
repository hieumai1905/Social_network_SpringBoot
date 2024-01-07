package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHashTagRepository extends JpaRepository<Hashtag, String> {
    Optional<Hashtag> findByHashtag(String hashtag);
}
