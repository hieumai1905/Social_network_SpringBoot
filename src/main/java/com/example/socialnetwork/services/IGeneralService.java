package com.example.socialnetwork.services;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T, K> {
    Optional<T> save(T object);

    boolean delete(T object);
}
