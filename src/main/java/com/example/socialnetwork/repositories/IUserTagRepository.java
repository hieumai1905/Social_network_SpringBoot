package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.UserTag;
import com.example.socialnetwork.models.key.UserTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTagRepository extends JpaRepository<UserTag, UserTagId> {
}
