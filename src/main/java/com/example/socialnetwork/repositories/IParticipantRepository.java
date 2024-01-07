package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.models.key.ParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipantRepository extends JpaRepository<Participant, ParticipantId> {
}
