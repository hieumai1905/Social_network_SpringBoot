package com.example.socialnetwork.repositories;


import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Query(value = "SELECT u.* FROM users u INNER JOIN relations r ON u.user_id = r.user_target_id WHERE r.type = :type AND r.user_id = :userId", nativeQuery = true)
    List<User> findAllByRelationTypeForUser(String type, String userId);
    // nhung moi quan he ma nguoi do thiet lap

    @Query(value = "SELECT u.* FROM users u INNER JOIN relations r ON u.user_id = r.user_id WHERE r.type = :type AND r.user_target_id = :userId", nativeQuery = true)
    List<User> findAllByRelationTypeOfUser(String type, String userId);
    // - nhung moi quan he ma minh thiet lap

    List<User> findAllByFullNameContaining(String name);

    @Query(value = "SELECT u.* " +
            "FROM users u INNER JOIN relations r ON u.user_id = r.user_target_id " +
            "WHERE r.type = 'FRIEND' " +
            "AND r.user_id = :userId " +
            "AND u.full_name LIKE %:name%", nativeQuery = true)
    List<User> findAllByNameContainingInFriends(String name, String userId);
}
