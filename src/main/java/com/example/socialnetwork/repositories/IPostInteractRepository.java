package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.PostInteract;
import com.example.socialnetwork.models.enums.InteractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostInteractRepository extends JpaRepository<PostInteract, Long> {
}
