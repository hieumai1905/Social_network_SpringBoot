package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRelationRepository extends JpaRepository<Relation, Long> {
    @Query(value = "SELECT * FROM relations WHERE user_id = :userId AND user_target_id = :userTargetId", nativeQuery = true)
    List<Relation> findByUserUserIdAndAndUserTarget_UserId(String userId, String userTargetId);

    @Query(value = "DELETE FROM relations WHERE user_id = :userId AND user_target_id = :userTargetId", nativeQuery = true)
    @Modifying
    void removeAllByUser_UserIdAndAndUserTarget_UserId(String userId, String userTargetId);

    @Query(value = "SELECT * FROM relations WHERE user_id = :userId AND user_target_id = :userTargetId AND type = :type LIMIT 1", nativeQuery = true)
    Optional<Relation> findByUserIdAndUserTargetIdAndType(String userId, String userTargetId, String type);
}
