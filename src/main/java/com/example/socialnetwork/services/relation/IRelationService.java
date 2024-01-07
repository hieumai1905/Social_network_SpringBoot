package com.example.socialnetwork.services.relation;

import com.example.socialnetwork.models.Relation;
import com.example.socialnetwork.models.enums.RelationType;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IRelationService extends IGeneralService<Relation, Long> {

    List<Relation> findByUserIdAndOtherTargetId(String userId, String userTargetId);

    void removeAllByUserIdAndUserTargetId(String userId, String userTargetId);

    Optional<Relation> findByUserIdAndUserTargetIdAndType(String userId, String userTargetId, RelationType type);

    void removeAllOfUserAndUserTarget(String userId, String userTargetId);
}
