package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {
    void deleteAllByPost_PostId(String postId);
}
