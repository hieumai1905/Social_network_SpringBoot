package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.UserHobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserHobbiesRepository extends JpaRepository<UserHobbies, String> {
    List<UserHobbies> findAllByUser_UserId(String userId);
}
