package com.example.socialnetwork.services.participant;

import com.example.socialnetwork.models.Participant;
import com.example.socialnetwork.repositories.IParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService implements IParticipantService {

    @Autowired
    private IParticipantRepository participantRepository;
    @Override
    public Optional<Participant> save(Participant object) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Participant object) {
        return false;
    }

    @Override
    public List<Participant> getParticipantsByConversationId(Long conversationId) {
        return participantRepository.getParticipantsByConversationId(conversationId);
    }
}
