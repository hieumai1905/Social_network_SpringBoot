package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, String> {

    @Query(value = "SELECT p.* " +
            "FROM posts p " +
            "WHERE ( " +
            "        (p.user_id = :userId " +
            "            OR (p.user_id IN (SELECT r.user_target_id " +
            "                              FROM relations r " +
            "                              WHERE r.user_id = :userId " +
            "                                AND r.type = 'FRIEND') " +
            "                AND p.access IN ('PUBLIC', 'FRIEND')))" +
            "        OR p.post_id IN (SELECT ut.post_id " +
            "                         FROM user_tags ut " +
            "                         WHERE ut.user_id = :userId)" +
            "        OR (p.user_id IN (SELECT r.user_target_id " +
            "                          FROM relations r " +
            "                          WHERE r.user_id = :userId " +
            "                            AND r.type = 'FOLLOW') " +
            "        AND p.access = 'PUBLIC')" +
            "    ) " +
            "  AND NOT EXISTS (SELECT 1 " +
            "                  FROM post_interacts pi " +
            "                  WHERE pi.user_id = :userId " +
            "                    AND (pi.type = 'HIDDEN' OR pi.type = 'REPORT') " +
            "                    AND pi.post_id = p.post_id)" +
            " " +
            "UNION " +
            " " +
            "SELECT p.* " +
            "FROM posts p " +
            "WHERE p.post_id IN (SELECT s.post_id " +
            "                    FROM post_interacts s " +
            "                    WHERE s.user_id = :userId " +
            "                    AND s.type = 'SHARED' " +
            "                    )" +
            " " +
            "ORDER BY create_at DESC", nativeQuery = true)
    List<Post> findAllPostForNewsFeed(String userId);

    @Query(value = "SELECT p.* " +
            "FROM posts p " +
            "WHERE p.post_id IN (SELECT pi.post_id " +
            "                    FROM post_interacts pi " +
            "                    WHERE pi.user_id = :userId " +
            "                      AND pi.type = :type) " +
            "ORDER BY p.create_at DESC", nativeQuery = true)
    List<Post> findAllByUser_UserIdAndByType(String userId, String type);

    @Query(value = "SELECT p.* " +
            "FROM posts p " +
            "WHERE (p.user_id = :userId OR p.post_id IN " +
            "(SELECT ut.post_id FROM user_tags ut WHERE ut.user_id = :userId)) " +
            "  AND NOT EXISTS (SELECT 1 " +
            "                  FROM post_interacts pi " +
            "                  WHERE pi.user_id = :userId " +
            "                    AND (pi.type = 'HIDDEN') " +
            "                    AND pi.post_id = p.post_id) " +
            "UNION " +
            "SELECT p.* " +
            "FROM posts p " +
            "WHERE p.post_id IN (SELECT s.post_id " +
            "                    FROM post_interacts s " +
            "                    WHERE s.user_id = :userId " +
            "                    AND s.type = 'SHARED') " +
            "ORDER BY create_at DESC", nativeQuery = true)
    List<Post> findAllByUserForProfileOfMe(String userId);

    @Query(value = "SELECT * FROM (" +
            "SELECT p.* " +
            "FROM posts p " +
            "WHERE (p.user_id = :userId OR p.post_id IN " +
            "(SELECT ut.post_id FROM user_tags ut WHERE ut.user_id = :userId)) " +
            "AND NOT EXISTS (SELECT 1 " +
            "               FROM post_interacts pi " +
            "               WHERE pi.user_id = :userId " +
            "               AND (pi.type = 'HIDDEN') " +
            "               AND pi.post_id = p.post_id) " +
            "AND p.access = 'PUBLIC'" +
            "UNION " +
            "SELECT p.* " +
            "FROM posts p " +
            "WHERE p.post_id IN (SELECT s.post_id " +
            "                    FROM post_interacts s " +
            "                    WHERE s.user_id = :userId " +
            "                    AND s.type = 'SHARED')) AS subquery " +
            "ORDER BY subquery.create_at DESC", nativeQuery = true)
    List<Post> findAllByUserForProfile(String userId);

    @Query(value = "SELECT p.* " +
            "FROM posts p " +
            "WHERE (p.user_id = :userId OR p.post_id IN " +
            "(SELECT ut.post_id FROM user_tags ut WHERE ut.user_id = :userId)) " +
            "AND NOT EXISTS (SELECT 1 " +
            "               FROM post_interacts pi " +
            "               WHERE pi.user_id = :userId " +
            "               AND (pi.type = 'HIDDEN') " +
            "               AND pi.post_id = p.post_id) " +
            "AND (p.access = 'PUBLIC' OR p.access = 'FRIEND') " +
            "UNION " +
            "SELECT p.* " +
            "FROM posts p " +
            "WHERE p.post_id IN (SELECT s.post_id " +
            "                    FROM post_interacts s " +
            "                    WHERE s.user_id = :userId " +
            "                    AND s.type = 'SHARED') " +
            "ORDER BY create_at DESC", nativeQuery = true)
    List<Post> findAllByUserForProfileOfFriend(String userId);

    @Query(value = "SELECT p.* " +
            "FROM posts p " +
            "JOIN post_hashtags ph ON p.post_id = ph.post_id " +
            "JOIN hash_tags h ON ph.hashtag_id = h.hashtag_id " +
            "WHERE h.hashtag = :hashtag " +
            "AND p.post_id NOT IN (SELECT pi.post_id " +
            "                      FROM post_interacts pi " +
            "                      WHERE pi.type = 'HIDDEN') " +
            "AND p.user_id NOT IN (SELECT r.user_target_id " +
            "                      FROM relations r " +
            "                      WHERE r.user_id = :userId " +
            "                      AND r.type = 'BLOCK') " +
            "AND p.user_id NOT IN (SELECT r.user_id " +
            "                      FROM relations r " +
            "                      WHERE r.user_target_id = :userId " +
            "                      AND r.type = 'BLOCK') " +
            "AND p.access = 'PUBLIC' " +
            "ORDER BY CASE " +
            "             WHEN p.user_id IN (SELECT r.user_target_id " +
            "                                FROM relations r " +
            "                                WHERE r.user_id = :userId " +
            "                                AND r.type IN ('FRIEND', 'FOLLOW')) THEN 0 " +
            "             ELSE 1 END, " +
            "         p.create_at DESC", nativeQuery = true)
    List<Post> findAllByHashtagForUserId(String hashtag, String userId);

    List<Post> findAllByUser_UserIdOrderByCreateAtDesc(String userId);
}
