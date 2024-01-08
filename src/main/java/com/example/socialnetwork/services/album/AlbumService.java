package com.example.socialnetwork.services.album;

import com.example.socialnetwork.models.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {
    @Override
    public Optional<Album> save(Album object) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Album object) {
        return false;
    }

}
