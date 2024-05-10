package com.example.socialnetwork.services.relation;

import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.repositories.IRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RelationService implements IRelationService {
    @Autowired
    private IRelationRepository relationRepository;

    @Override
    public Optional<Relation> save(Relation object) {
        return Optional.of(relationRepository.save(object));
    }

    @Override
    public boolean delete(Relation object) {
        try {
            relationRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Relation> findByUserIdAndOtherTargetId(String userId, String userTargetId) {
        return relationRepository.findByUserUserIdAndAndUserTarget_UserId(userId, userTargetId);
    }

    @Override
    public void removeAllByUserIdAndUserTargetId(String userId, String userTargetId) {
        relationRepository.removeAllByUser_UserIdAndAndUserTarget_UserId(userId, userTargetId);
    }

    @Override
    public Optional<Relation> findByUserIdAndUserTargetIdAndType(String userId, String userTargetId, RelationType type) {
        return relationRepository.findByUserIdAndUserTargetIdAndType(userId, userTargetId, String.valueOf(type));
    }

    @Override
    public void removeAllOfUserAndUserTarget(String userId, String userTargetId) {
        removeAllByUserIdAndUserTargetId(userId, userTargetId);
        removeAllByUserIdAndUserTargetId(userTargetId, userId);
    }

    @Override
    public List<Relation> findByUserTargetIdAndType(String userId, RelationType type) {
        return relationRepository.findAllByUserTarget_UserIdAndTypeOrderBySetAtDesc(userId, type);
    }

    @Override
    public List<Relation> findByUserIdAndType(String userId, RelationType type) {
        return relationRepository.findAllByUser_UserIdAndTypeOrderBySetAtDesc(userId, type);
    }

    @Override
    public Long countMutualFriends(String userId1, String userId2) {
        return relationRepository.countMutualFriends(userId1, userId2);
    }
}
