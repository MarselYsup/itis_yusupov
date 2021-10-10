package ru.itis.sign.database;

import ru.itis.sign.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByName(String name);
    List<String> findAllNames();
}
