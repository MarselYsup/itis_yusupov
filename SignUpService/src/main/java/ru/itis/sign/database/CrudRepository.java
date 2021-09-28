package ru.itis.sign.database;


import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(K id);
    Optional<T> findByName(String name);
    List<String> findAllNames();
    List<T> findAll();
    T save(T item);
    void update(K id, T item);
    void delete(K id);
}

