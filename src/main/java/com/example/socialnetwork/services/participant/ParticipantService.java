package com.example.socialnetwork.services.participant;

import com.example.socialnetwork.models.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService implements IParticipantService {
    @Override
    public Optional<Participant> save(Participant object) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Participant object) {
        return false;
    }
}
