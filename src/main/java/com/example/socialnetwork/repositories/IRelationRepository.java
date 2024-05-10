package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.enums.RelationType;
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

    List<Relation> findAllByUserTarget_UserIdAndTypeOrderBySetAtDesc(String userTargetId, RelationType type);

    List<Relation> findAllByUser_UserIdAndTypeOrderBySetAtDesc(String userTargetId, RelationType type);

    @Query(value = "SELECT COUNT(*) " +
            "FROM relations r1 " +
            "JOIN relations r2 ON r1.user_target_id = r2.user_target_id " +
            "WHERE r1.user_id = :userId1 AND r2.user_id = :userId2 AND r1.type = 'friend' AND r2.type = 'friend'",
            nativeQuery = true)
    Long countMutualFriends(String userId1, String userId2);
}
