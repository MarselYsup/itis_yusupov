package ru.itis.sign.database;

import ru.itis.sign.models.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends CrudRepository<Avatar,Long>{
    Optional<Avatar> findByUserId(Long userId);
}
